package loan.management.module.lms.controller.dto;

import loan.management.module.lms.service.LoanRequestService;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanStatusResponseDto {
    private Long customerNumber;
    private BigDecimal amount;
    private LoanStatusType status;
    private LocalDateTime createdAt;
}
