package com.ntthuat.auth.entity;

import com.ntthuat.auth.constant.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author ntthuat
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "public", name = "roles")
public class Role extends BaseEntity{

    @Id
    @Column(name = "role_id", columnDefinition = "serial")
    @GeneratedValue
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "role_name", length = 60)
    private RoleName roleName;

    @Override
    public Long getId() {
        return roleId;
    }

}
