package com.ntthuat.auth.mapper;

import com.ntthuat.auth.dto.SignUpRequest;
import com.ntthuat.auth.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @author ntthuat
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @InheritInverseConfiguration()
    User map(SignUpRequest signUpRequest);
}
