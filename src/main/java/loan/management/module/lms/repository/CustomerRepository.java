package loan.management.module.lms.repository;

import loan.management.module.lms.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean countByCustomerNumber(Long customerNumber);
}
