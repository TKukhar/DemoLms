package loan.management.module.lms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import loan.management.module.lms.controller.dto.ClientRegistrationRequest;
import loan.management.module.lms.controller.dto.ClientRegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class ScoringService {

    @Value("${scoring.base-url}")
    private String scoringBaseUrl;

    @Value("${scoring.registration-url}")
    private String registrationUrl;

    @Value("${scoring.client.name}")
    private String clientName;

    @Value("${scoring.client.url}")
    private String callbackUrl;

    @Value("${scoring.client.username}")
    private String clientUsername;

    @Value("${scoring.client.password}")
    private String clientPassword;

    private final RestTemplate restTemplate = new RestTemplate();

    private String clientToken;
    private String scoringToken;

    public Map getScoringResult(Long customerNumber) {
        ensureClientToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("client-token", clientToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            // 1. INITIATE SCORING
            ObjectMapper mapper = new ObjectMapper();
            log.info("Calling initiateQueryScore for customerNumber: {}", customerNumber);
            ResponseEntity<String> initiate = restTemplate.exchange(
                    scoringBaseUrl + "/scoring/initiateQueryScore/" + customerNumber,
                    HttpMethod.GET,
                    request,
                    String.class
            );

            if (initiate.getStatusCode().is2xxSuccessful()
                    && initiate.getBody() != null          ) {

                 scoringToken = initiate.getBody();

                if (scoringToken == null) {
                    throw new RuntimeException("Scoring token not present in response");
                }



            } else {
                log.warn("Scoring token not present in initiateQueryScore response: {}", initiate.getBody());

                throw new RuntimeException("Invalid response from scoring engine: " + initiate.getBody());
            }


            // 2. RETRY queryScore
            int maxRetries = 5;
            int delayMs = 2000;
            log.info("Querying score with token: {}", scoringToken);

            for (int attempt = 1; attempt <= maxRetries; attempt++) {
                try {
                    ResponseEntity<Map> result = restTemplate.exchange(
                            scoringBaseUrl + "/scoring/queryScore/" + scoringToken,
                            HttpMethod.GET,
                            request,
                            Map.class
                    );

                    if (result.getStatusCode().is2xxSuccessful()) {
                        log.info("Received scoring result: {}", result.getBody());

                        return result.getBody();

                    }

                } catch (HttpClientErrorException.NotFound ex) {
                    System.out.println("Attempt " + attempt + ": score not ready yet.");
                }

                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    throw new RuntimeException("Interrupted during retry delay", e);
                }
            }

            log.error("Scoring not available after {} retries for customer {}", maxRetries, customerNumber);

            throw new RuntimeException("Scoring result not available after max retries");

        } catch (HttpClientErrorException.Unauthorized ex) {
            clientToken = null;
            return null;
        }
    }


    private void ensureClientToken() {
        if (clientToken != null) return;

        ClientRegistrationRequest req = new ClientRegistrationRequest(
                callbackUrl, clientName, clientUsername, clientPassword
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClientRegistrationRequest> request = new HttpEntity<>(req, headers);

        ResponseEntity<ClientRegistrationResponse> response = restTemplate.exchange(
                registrationUrl,
                HttpMethod.POST,
                request,
                ClientRegistrationResponse.class
        );

        clientToken = response.getBody().getToken();
    }
}
