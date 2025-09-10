package org.sampong.onlinebanking.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;

@Entity
@Table(name = "usr_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private String username;
    private String password;
    private String email;
    private Boolean enabled;
    private Boolean admin;

    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "user")
    private UserDetail userDetail;
}
