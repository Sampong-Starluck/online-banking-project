package org.sampong.onlinebanking.transfer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;
import org.sampong.onlinebanking._common.enumerate.Currency;
import org.sampong.onlinebanking._common.enumerate.TranceStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "trn_dead_letter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeadLetterTransaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_account_id")
    private Long fromAccountId;
    @Column(name = "to_account_id")
    private Long toAccountId;
    private Double amount;
    private String type;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "retrty_count")
    private int retryCount = 0;
    @Enumerated(EnumType.STRING)
    private TranceStatus trxnStatus = TranceStatus.PENDING; // PENDING, SUCCESS, FAILED_FINAL

    private LocalDateTime failedAt = LocalDateTime.now();
}