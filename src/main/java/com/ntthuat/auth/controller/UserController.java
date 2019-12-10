package com.ntthuat.auth.controller;

import com.ntthuat.auth.dto.UserDTO;
import com.ntthuat.auth.dto.UserLookupParams;
import com.ntthuat.auth.repository.UserRepository;
import com.ntthuat.auth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    public Page<UserDTO> findAll(@ModelAttribute UserLookupParams params, @PageableDefault(sort = "userId"
            , direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findAll(params, pageable);
    }
}
