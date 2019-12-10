package com.ntthuat.auth.service;

import com.ntthuat.auth.dto.ListingResponse;
import com.ntthuat.auth.repository.RepositoryExtensions;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ntthuat
 */
// TODO: move this to common
public interface ResponseFactory {

/*    default <T, E> Response.ResponseBuilder<T, E> builder() {
        return builder(Response.builder().build());
    }

    <T, E> Response.ResponseBuilder<T, E> builder(Response response);*/

    ListingResponse pagingResponse(List content, Pageable pageable, RepositoryExtensions repository);

    ListingResponse pagingResponse(List content, Pageable pageable, long total);
}
