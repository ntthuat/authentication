package com.ntthuat.auth.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema="public", name="access_token")
public class AccessToken extends BaseEntity {

    @Id
    @Column(name="id", columnDefinition="bigserial")
    @GeneratedValue
    Long id;

    @Column(name = "access_token")
    String accessToken;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "request_date")
    ZonedDateTime requestDatetime;

    @Column(name = "expiration")
    Boolean expiration;

    public boolean isExpired() {
        return expiration;
    }

    @Override
    public Long getId() {
        return id;
    }
}
