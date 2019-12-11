package com.ntthuat.auth.dto;

import com.ntthuat.auth.constant.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ntthuat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long roleId;

    private RoleName roleName;
}
