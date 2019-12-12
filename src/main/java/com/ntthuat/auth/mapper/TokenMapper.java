package com.ntthuat.auth.mapper;

import com.ntthuat.auth.dto.AccessTokenDTO;
import com.ntthuat.auth.entity.AccessToken;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 *
 * @author ntthuat
 */
@Mapper(componentModel = "spring")
public interface TokenMapper {

    AccessTokenDTO map(AccessToken entity);

    @InheritInverseConfiguration()
    AccessToken map(AccessTokenDTO dto);
}
