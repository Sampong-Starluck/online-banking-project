package org.sampong.onlinebanking.customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "cus_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private String refNo;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private LocalDate dateOfBirth;
}
