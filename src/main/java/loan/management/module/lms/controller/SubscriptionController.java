package loan.management.module.lms.controller;

import io.credable.cbs.customer.Customer;
import loan.management.module.lms.service.CustomerKycService;
import loan.management.module.lms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscribe")
public class SubscriptionController {

    private final CustomerKycService customerKycService;

    private final CustomerService customerService;

    @Autowired
    public SubscriptionController(CustomerKycService customerKycService, CustomerService customerService) {
        this.customerKycService = customerKycService;
        this.customerService = customerService;
    }

    @PostMapping("/{customerNumber}")
    public ResponseEntity<Customer> getKycCustomer(@PathVariable String customerNumber) {
        Customer customer = customerKycService.getCustomerKyc(customerNumber);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerService.saveCustomerDataFromKyc(customer);
        return ResponseEntity.ok(customer);
    }
}