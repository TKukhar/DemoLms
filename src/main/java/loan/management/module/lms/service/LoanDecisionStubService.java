package loan.management.module.lms.service;

import loan.management.module.lms.controller.dto.LoanStatusType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class LoanDecisionStubService {

    public LoanStatusType decideLoanStatus(BigDecimal requestedAmount, Map<String, Object> scoringResponse) {
        // 1 and 3 will be executed when data will be real
        // 1. Check exclusion
//        String exclusion = (String) scoringResponse.getOrDefault("exclusion", "No Exclusion");
//        if (!"No Exclusion".equalsIgnoreCase(exclusion)) {
//            return LoanStatusType.REJECTED;
//        }

        // 2. Check loan amount against allowed limit
        Double limitRaw = (Double) scoringResponse.getOrDefault("limitAmount", 0.0);
        if (limitRaw.equals(0d)) {
            limitRaw = 1000d;
        }
        if (requestedAmount.compareTo(BigDecimal.valueOf(limitRaw)) > 0) {
            return LoanStatusType.REJECTED;
        }

        // 3. Check scoring value (e.g. minimum score of 5000 is required)
//        BigDecimal score = (BigDecimal) scoringResponse.getOrDefault("score", 0);
//        if (score.compareTo(new BigDecimal(5000)) > 0) {
//            return LoanStatusType.REJECTED;
//        }

        // If all conditions are met — approve
        return LoanStatusType.APPROVED;
    }

}

