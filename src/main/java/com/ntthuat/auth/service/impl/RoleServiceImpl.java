package com.ntthuat.auth.service.impl;

import com.ntthuat.auth.dto.*;
import com.ntthuat.auth.entity.Role;
import com.ntthuat.auth.mapper.RoleMapper;
import com.ntthuat.auth.repository.RoleRepository;
import com.ntthuat.auth.service.RoleService;
import com.ntthuat.auth.service.specification.RoleSpecification;
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
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    final RoleMapper roleMapper;

    @Override
    public Page<RoleDTO> findAll(RoleLookupParams params, Pageable pageable) {
        Specification<Role> specification = new RoleSpecification(params);
        return roleRepository
                .findAll(specification, pageable)
                .map(roleMapper::map);
    }

    @Override
    public ListingResponse buildResponse(List data, Pageable pageable) {
        // TODO
        return null;
    }
}
