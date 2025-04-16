package loan.management.module.lms.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanRequestDto {
    private Long customerNumber;
    private BigDecimal amount;
}
