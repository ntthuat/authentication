package com.ntthuat.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * @author ntthuat
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {

    String accessToken;

    String refreshToken;

    Boolean expiration;

    ZonedDateTime requestDatetime;
}
