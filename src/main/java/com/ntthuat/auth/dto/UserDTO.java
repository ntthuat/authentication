package com.ntthuat.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ntthuat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private boolean active;

    private boolean tokenExpired;

    private String ssoId;
}
