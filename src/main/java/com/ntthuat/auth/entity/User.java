package com.ntthuat.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "public", name = "users")
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id", columnDefinition = "serial")
    @GeneratedValue
    private Long userId;

    @NaturalId
    @Column(name = "user_name", length = 64, nullable = false)
    private String userName;

    @Column(name = "password_", length = 64, nullable = false)
    private String password;

    @NaturalId
    @Email
    @Column(name = "email", length = 254, nullable = false)
    private String email;

    @Column(name = "first_name", length = 64)
    private String firstName;

    @Column(name = "last_name", length = 64)
    private String lastName;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "token_expired")
    private boolean tokenExpired;

    @Column(name = "sso_id", length = 20)
    private String ssoId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id"))
    private Collection<Role> roles;

    @Override
    public Long getId() {
        return userId;
    }
}
