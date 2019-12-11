package com.ntthuat.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

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

    private Collection<RoleDTO> roles;
}
