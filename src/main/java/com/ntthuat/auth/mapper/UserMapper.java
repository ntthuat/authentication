package com.ntthuat.auth.mapper;

import com.ntthuat.auth.dto.SignUpRequest;
import com.ntthuat.auth.dto.UserDTO;
import com.ntthuat.auth.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * @author ntthuat
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO map(User entity);

    @InheritInverseConfiguration()
    User map(UserDTO dto);

    @InheritInverseConfiguration()
    User map(SignUpRequest signUpRequest);
}
