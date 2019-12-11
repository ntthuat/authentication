package com.ntthuat.auth.controller;

import com.ntthuat.auth.dto.RoleDTO;
import com.ntthuat.auth.dto.RoleLookupParams;
import com.ntthuat.auth.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ntthuat
 */
@Slf4j
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    final RoleService roleService;

    @GetMapping
    public Page<RoleDTO> findAll(@ModelAttribute RoleLookupParams params, @PageableDefault(sort = "roleId"
            , direction = Sort.Direction.DESC) Pageable pageable) {
        return roleService.findAll(params, pageable);
    }
}
