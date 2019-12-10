package com.ntthuat.auth.service;

import com.ntthuat.auth.dto.ListingResponse;
import com.ntthuat.auth.dto.UserDTO;
import com.ntthuat.auth.dto.UserLookupParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ntthuat
 */
public interface UserService {

    Page<UserDTO> findAll(UserLookupParams params, Pageable pageable);

    ListingResponse buildResponse(List data, Pageable pageable);
}
