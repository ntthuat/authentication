package com.ntthuat.auth.service.impl;

import brave.Tracer;
import com.ntthuat.auth.dto.ListingResponse;
import com.ntthuat.auth.repository.RepositoryExtensions;
import com.ntthuat.auth.service.ResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.PropertyResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ntthuat
 */
@Slf4j
@Component
// TODO: move this to common
public class DefaultResponseFactory implements ResponseFactory {

    final String applicationName;

    final Tracer tracer;

    public DefaultResponseFactory(PropertyResolver properties, Tracer tracer) {
        this.applicationName = properties.getProperty("spring.application.name", "");
        this.tracer = tracer;
    }

    @Override
    public ListingResponse pagingResponse(List content, Pageable pageable, RepositoryExtensions repository) {
        Page page = new PageImpl(content, pageable, repository.count());
        return ListingResponse.builder().data(content).totalPage(page.getTotalPages()).build();
    }

    @Override
    public ListingResponse pagingResponse(List content, Pageable pageable, long total) {
        Page page = new PageImpl(content, pageable, total);
        return ListingResponse.builder().data(content).totalPage(page.getTotalPages()).build();
    }
}
