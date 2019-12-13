package com.ntthuat.auth.repository;

import com.ntthuat.auth.entity.AccessToken;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author ntthuat
 */
public interface TokenRepository extends RepositoryExtensions<AccessToken, Long>, JpaSpecificationExecutor<AccessToken> {

    Optional<AccessToken> findAccessTokenByAccessToken(String accessToken);

    boolean existsAccessTokenByAccessToken(String accessToken);
}
