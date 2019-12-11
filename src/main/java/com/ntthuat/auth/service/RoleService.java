package com.ntthuat.auth.service;

import com.ntthuat.auth.dto.ListingResponse;
import com.ntthuat.auth.dto.RoleDTO;
import com.ntthuat.auth.dto.RoleLookupParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ntthuat
 */
public interface RoleService {

    Page<RoleDTO> findAll(RoleLookupParams params, Pageable pageable);

    ListingResponse buildResponse(List data, Pageable pageable);
}
