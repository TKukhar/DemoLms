package loan.management.module.lms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(name = "customer_number", nullable = false, unique = true)
    private Long customerNumber;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private ZonedDateTime dob;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private Long mobile;

    @Column(name = "monthly_income")
    private Double monthlyIncome;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
