package com.ntthuat.auth.controller;

import com.ntthuat.auth.constant.RoleName;
import com.ntthuat.auth.dto.ApiResponse;
import com.ntthuat.auth.dto.LoginRequest;
import com.ntthuat.auth.dto.SignUpRequest;
import com.ntthuat.auth.entity.AccessToken;
import com.ntthuat.auth.entity.Role;
import com.ntthuat.auth.entity.User;
import com.ntthuat.auth.exception.AuthenticationException;
import com.ntthuat.auth.mapper.UserMapper;
import com.ntthuat.auth.repository.TokenRepository;
import com.ntthuat.auth.repository.UserRepository;
import com.ntthuat.auth.security.JwtAuthenticationResponse;
import com.ntthuat.auth.security.JwtTokenProvider;
import com.ntthuat.auth.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

/**
 * @author ntthuat
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    final AuthenticationManager authenticationManager;

    final PasswordEncoder passwordEncoder;

    final JwtTokenProvider tokenProvider;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final TokenRepository tokenRepository;

    final UserMapper userMapper;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@ModelAttribute LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        // store token into DB
        final AccessToken token = new AccessToken();
        token.setAccessToken(jwt);
        token.setExpiration(true);
        tokenRepository.save(token, true);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @ModelAttribute SignUpRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = userMapper.map(signUpRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new AuthenticationException("User RoleName not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user, true);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUserName()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/signout")
    public ResponseEntity logoutUser(@RequestHeader(name = "Authorization") String bearerToken) {

        final String jwt = getJwtFromBearerToken(bearerToken);
        if (!StringUtils.isEmpty(jwt) && tokenRepository.existsAccessTokenByAccessToken(jwt)) {
            AccessToken token = tokenRepository.findAccessTokenByAccessToken(jwt).get();
            token.setExpiration(false);
            tokenRepository.save(token);
        }

        return ResponseEntity.ok().body(new ApiResponse(true, "User logged out successfully"));
    }

    private String getJwtFromBearerToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
