package org.sampong.onlinebanking.transfer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking._common.enumerate.TranceStatus;
import org.sampong.onlinebanking._common.enumerate.TranceType;
import org.sampong.onlinebanking.account.model.Account;

@Entity
@Table(name = "trn_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "src_acc_number", nullable = false, updatable = false)
    private String sourceAccountNumber = null;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "src_acc_id", nullable = false, updatable = false, referencedColumnName = "id")
    private Account sourceAccount = null;
    @Column(name = "des_acc_number", nullable = false, updatable = false)
    private String destinationAccountNumber = null;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "des_acc_id", nullable = false, updatable = false, referencedColumnName = "id")
    private Account destinatioAccount = null;
    private Double amount = null;
    private Currency currency = null;
    private String description = null;
    private String tranceNumber = null;
    private TranceType tranceType = null;
}
