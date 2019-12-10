package com.ntthuat.auth.dto;

import com.ntthuat.auth.validation.ValidCredentials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ntthuat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidCredentials
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
