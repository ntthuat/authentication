package com.ntthuat.auth.service.impl;

import com.ntthuat.auth.dto.ListingResponse;
import com.ntthuat.auth.dto.UserDTO;
import com.ntthuat.auth.dto.UserLookupParams;
import com.ntthuat.auth.entity.User;
import com.ntthuat.auth.mapper.UserMapper;
import com.ntthuat.auth.repository.UserRepository;
import com.ntthuat.auth.service.ResponseFactory;
import com.ntthuat.auth.service.UserService;
import com.ntthuat.auth.service.specification.UserSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ntthuat
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final UserMapper userMapper;

    final ResponseFactory factory;

    @Override
    public Page<UserDTO> findAll(UserLookupParams params, Pageable pageable) {
        Specification<User> specification = new UserSpecification(params);
        return userRepository
                .findAll(specification, pageable)
                .map(userMapper::map);
    }

    @Override
    public ListingResponse buildResponse(List data, Pageable pageable) {
        return factory.pagingResponse(data, pageable, userRepository);
    }
}
