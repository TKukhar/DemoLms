package loan.management.module.lms.repository;

import loan.management.module.lms.controller.dto.LoanStatusType;
import loan.management.module.lms.entity.LoanRequest;
import loan.management.module.lms.service.LoanRequestService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    boolean existsByCustomerNumberAndStatusIn(Long customerNumber, List<LoanStatusType> statuses);
    Optional<LoanRequest> findTopByCustomerNumberOrderByCreatedAtDesc(Long customerNumber);

}

