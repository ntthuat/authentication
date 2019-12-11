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
// TODO: cannot use now
public class RoleLookupParams {

    private Long roleId;

    private String roleName;
}
