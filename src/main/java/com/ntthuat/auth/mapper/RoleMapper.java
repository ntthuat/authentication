package com.ntthuat.auth.mapper;

import com.ntthuat.auth.dto.RoleDTO;
import com.ntthuat.auth.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @author ntthuat
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO map(Role entity);

    @InheritInverseConfiguration()
    Role map(RoleDTO dto);
}
