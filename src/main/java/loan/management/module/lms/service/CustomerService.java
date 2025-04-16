package loan.management.module.lms.service;

import io.credable.cbs.customer.Customer;
import loan.management.module.lms.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class CustomerService {
    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void saveCustomerDataFromKyc(Customer customer) {
        loan.management.module.lms.entity.Customer save = mapToEntity(customer);
        log.info("Get customer kyc by customer number: {}", save.getCustomerNumber());
        customerRepository.save(save);
    }

    public static loan.management.module.lms.entity.Customer mapToEntity(Customer source) {
        return loan.management.module.lms.entity.Customer.builder()
                .customerNumber(Long.valueOf(source.getCustomerNumber()))
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .middleName(source.getMiddleName())
                .email(source.getEmail())
                .mobile(Long.valueOf(source.getMobile()))
                .monthlyIncome(source.getMonthlyIncome())
                .gender(source.getGender() != null ? source.getGender().name() : null)
                .idNumber(source.getIdNumber())
                .idType(source.getIdType() != null ? source.getIdType().name() : null)
                .status(source.getStatus() != null ? source.getStatus().name() : null)
                .dob(convertToZonedDateTime(source.getDob()))
                .createdAt(convertToZonedDateTime(source.getCreatedAt()))
                .createdDate(convertToZonedDateTime(source.getCreatedDate()))
                .updatedAt(convertToZonedDateTime(source.getUpdatedAt()))
                .build();
    }

    public static ZonedDateTime convertToZonedDateTime(XMLGregorianCalendar xmlDate) {
        if (xmlDate == null) return null;
        Instant instant = xmlDate.toGregorianCalendar().toInstant();
        return instant.atZone(ZoneId.systemDefault()); // або ZoneId.of("Europe/Kyiv") якщо треба явно
    }


}
