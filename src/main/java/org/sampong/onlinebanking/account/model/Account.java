package org.sampong.onlinebanking.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;
import org.sampong.onlinebanking._common.enumerate.AccountType;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.customer.model.Customer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "acc_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(unique = true, nullable = false, name = "account_name")
    private String accountNumber;
    @Column(name = "account_issue_date")
    private LocalDateTime issueDate;
    @Column(name = "account_expire_date")
    private LocalDateTime expireDate;
    @Column(name = "account_balance")
    private Double balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // Business methods
    public Boolean deposit(Double amount) {
        if (amount <= 0.0) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Deposit amount must be positive");
        }
        balance += amount;
        return true;
    }

    public Boolean withdraw(Double amount) {
        if (amount <= 0.0) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Insufficient funds");
        }
        balance -= amount;
        return true;
    }
}
