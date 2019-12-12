package com.ntthuat.auth.service.impl;

import com.ntthuat.auth.entity.AccessToken;
import com.ntthuat.auth.repository.TokenRepository;
import com.ntthuat.auth.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ntthuat
 */
@Service
@Slf4j
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    final TokenRepository tokenRepository;

    @Override
    public boolean validateToken(String token) {
        Optional<AccessToken> tokenOpt = tokenRepository.findAccessTokenByAccessToken(token);
        if (!tokenOpt.isPresent()) {
            log.error("Can not find token in DB");
            return false;
        }

        if (tokenOpt.get().isExpired()) {
            return true;
        } else {
            log.error("Token is expired");
            return false;
        }
    }
}
