package org.sampong.onlinebanking.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;
import org.sampong.onlinebanking._common.enumerate.IdType;

import java.time.LocalDateTime;

@Entity
@Table(name = "cus_identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerIdentification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "id_number")
    private String idNumber = null;
    @Column(name = "issue_date")
    private LocalDateTime issueDate = null;
    @Column(name = "expire_date")
    private LocalDateTime expireDate = null;
    @Enumerated(EnumType.STRING)
    private IdType idType = null;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer=null;
}
