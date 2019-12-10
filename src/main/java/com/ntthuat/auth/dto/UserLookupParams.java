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
public class UserLookupParams {

    private Long userId;

    private String userName;

    private String email;

    private boolean active;

    private String firstName;

    private String lastName;

    private String role;

    private boolean tokenExpired;

    private String ssoId;
}
