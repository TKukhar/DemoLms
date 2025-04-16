package loan.management.module.lms.entity;

import jakarta.persistence.*;
import loan.management.module.lms.controller.dto.LoanStatusType;
import loan.management.module.lms.service.LoanRequestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "loan_request")
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerNumber;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private LoanStatusType status;

    private LocalDateTime createdAt;
}
