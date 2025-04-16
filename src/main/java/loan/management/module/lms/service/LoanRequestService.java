package loan.management.module.lms.service;

import loan.management.module.lms.controller.dto.LoanRequestDto;
import loan.management.module.lms.controller.dto.LoanResponseDto;
import loan.management.module.lms.controller.dto.LoanStatusType;
import loan.management.module.lms.entity.LoanRequest;
import loan.management.module.lms.repository.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;
    private final LoanDecisionStubService loanDecisionService;
    private final ScoringService scoringService;

    public LoanResponseDto createLoanRequest(LoanRequestDto dto) {

        Map response = scoringService.getScoringResult(dto.getCustomerNumber());
        final List<LoanStatusType> activeStatuses = List.of(LoanStatusType.PENDING, LoanStatusType.APPROVED);

        if (loanRequestRepository.existsByCustomerNumberAndStatusIn(dto.getCustomerNumber(), activeStatuses)) {
            return new LoanResponseDto("REJECTED", "Existing active loan request");
        }

        LoanStatusType decision = loanDecisionService.decideLoanStatus(dto.getAmount(), response);

        LoanRequest request = new LoanRequest();
        request.setCustomerNumber(dto.getCustomerNumber());
        request.setAmount(dto.getAmount());
        request.setStatus(decision);
        request.setCreatedAt(LocalDateTime.now());

        loanRequestRepository.save(request);

        return new LoanResponseDto(decision.name(), decision == LoanStatusType.APPROVED
                ? "Loan approved successfully"
                : "Loan rejected by decision engine");
    }


}
