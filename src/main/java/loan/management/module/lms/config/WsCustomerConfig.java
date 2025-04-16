package loan.management.module.lms.config;

import io.credable.cbs.customer.CustomerPort;
import io.credable.cbs.customer.CustomerPortService;
import jakarta.xml.ws.BindingProvider;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WsCustomerConfig {

    @Bean
    public CustomerPort customerBindingProvider(@Value("${wsdl.user}") String user,
                                                @Value("${wsdl.password}") String password,
                                                @Value("${wsdl.customer.url}") String url) {
        CustomerPortService service = new CustomerPortService();
        CustomerPort port = service.getCustomerPortSoap11();
        ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url
        );

        applyWsSecurity(port, user, password);

        return port;
    }

    public static void applyWsSecurity(Object port, String username, String password) {
        Client client = ClientProxy.getClient(port);

        Map<String, Object> outProps = new HashMap<>();
        outProps.put(WSHandlerConstants.ACTION, "UsernameToken");
        outProps.put(WSHandlerConstants.USER, username);
        outProps.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");

        outProps.put(WSHandlerConstants.PW_CALLBACK_REF,
                new PasswordCallbackHandler(password));
        client.getOutInterceptors().add(new WSS4JOutInterceptor(outProps));
    }


    public static class PasswordCallbackHandler implements CallbackHandler {
        private final String password;

        public PasswordCallbackHandler(String password) {
            this.password = password;
        }

        @Override
        public void handle(Callback[] callbacks) {
            for (Callback callback : callbacks) {
                if (callback instanceof WSPasswordCallback pwcb) {

                    pwcb.setPassword(password);
                }
            }
        }

    }

}
