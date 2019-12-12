package com.ntthuat.auth.service;

/**
 * @author ntthuat
 */
public interface TokenService {

    boolean validateToken(String token);
}
