package org.sampong.onlinebanking.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sampong.onlinebanking._common.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usr_user_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_detail_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    List<Role> roles =  new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private Users user;
}
