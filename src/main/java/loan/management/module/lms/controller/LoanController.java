package loan.management.module.lms.controller;

import loan.management.module.lms.controller.dto.LoanRequestDto;
import loan.management.module.lms.controller.dto.LoanResponseDto;
import loan.management.module.lms.controller.dto.LoanStatusResponseDto;
import loan.management.module.lms.service.LoanRequestService;
import loan.management.module.lms.service.LoanStatusService;
import loan.management.module.lms.service.ScoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanRequestService loanService;

    private final LoanStatusService loanStatusService;
    
    private final ScoringService scoringService;

    @PostMapping("/request")
    public ResponseEntity<LoanResponseDto> requestLoan(@RequestBody LoanRequestDto loanRequest) {
        LoanResponseDto response = loanService.createLoanRequest(loanRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/loan-status/{customerNumber}")
    public ResponseEntity<LoanStatusResponseDto> getLoanStatus(@PathVariable Long customerNumber) {
        try {

            LoanStatusResponseDto response = loanStatusService.getLoanStatus(customerNumber );
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }}