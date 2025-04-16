package loan.management.module.lms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoanResponseDto {
    private String loanStatus;
    private String message;
}
