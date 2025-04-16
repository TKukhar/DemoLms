package loan.management.module.lms.service;

import io.credable.cbs.customer.Customer;
import io.credable.cbs.customer.CustomerPort;
import io.credable.cbs.customer.CustomerRequest;
import io.credable.cbs.customer.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerKycService {
    private final CustomerPort customerBindingProvider ;
    public Customer getCustomerKyc(String customerNumber) {
        log.info("Get customer kyc by customer key: {}", customerNumber);
        CustomerRequest request = new CustomerRequest();
        request.setCustomerNumber(customerNumber);

        CustomerResponse response = customerBindingProvider.customer(request);

        return response.getCustomer();
    }
    //to do негативний сценарій з неправмильною урлою і логін пароль  теж 403 помилок
}
