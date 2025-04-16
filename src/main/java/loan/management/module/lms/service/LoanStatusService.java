package loan.management.module.lms.service;

import loan.management.module.lms.controller.dto.LoanStatusResponseDto;
import loan.management.module.lms.entity.LoanRequest;
import loan.management.module.lms.repository.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoanStatusService {

    private final LoanRequestRepository loanRequestRepository;

    public LoanStatusResponseDto getLoanStatus(Long customerNumber) {
        LoanRequest request = loanRequestRepository
                .findTopByCustomerNumberOrderByCreatedAtDesc(customerNumber)
                .orElseThrow(() -> new NoSuchElementException("No loan request found"));

        LoanStatusResponseDto dto = new LoanStatusResponseDto();
        dto.setCustomerNumber(request.getCustomerNumber());
        dto.setAmount(request.getAmount());
        dto.setStatus(request.getStatus());
        dto.setCreatedAt(request.getCreatedAt());

        return dto;
    }
}

