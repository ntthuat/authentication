/*
package com.ntthuat.auth.pagination;

import com.google.common.primitives.Ints;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.PropertyResolver;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

*/
/**
 *
 * @author ntthuat
 *//*

@Component
@AllArgsConstructor
public class PageableWebFluxHandlerMethodArgumentResolver
        extends PageableHandlerMethodArgumentResolver
        implements HandlerMethodArgumentResolver {

    static final PageRequest DEFAULT_PAGE_REQUEST = PageRequest.of(0, 20);

    final PropertyResolver propertyResolver;

    final SortWebFluxHandlerMethodArgumentResolver sortResolver;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

    @Override
    @SuppressWarnings("UnstableApiUsage")
    public Object resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();

        Pageable defaults = getDefaultPageable(parameter);

        int page = Optional.ofNullable(params.getFirst(getPageParameterName()))
                .map(Ints::tryParse)
                .map(p -> isOneIndexedParameters() ? p - 1 : p)
                .map(p -> Ints.constrainToRange(p, 0, Integer.MAX_VALUE))
                .orElse(defaults.getPageNumber());

        int pageSize = Optional.ofNullable(params.getFirst(getSizeParameterName()))
                .map(Ints::tryParse)
                .map(ps -> Ints.constrainToRange(ps, 0, getMaxPageSize()))
                .orElse(defaults.getPageSize());

        return sortResolver
                .resolveArgument(parameter, bindingContext, exchange).cast(Sort.class)
                .map(sort -> PageRequest.of(
                        page, pageSize,
                        sort.isSorted() ? sort : defaults.getSort()
                ));
    }

    @Override
    protected boolean isOneIndexedParameters() {
        return Boolean.parseBoolean(propertyResolver.getProperty(
                "spring.data.web.pageable.one-indexed-parameters",
                "true"
        ));
    }

    private Pageable getDefaultPageable(MethodParameter parameter) {
        return Optional.ofNullable(parameter.getParameterAnnotation(PageableDefault.class))
                .map(defaults -> PageRequest.of(
                        defaults.page(), defaults.size(),
                        defaults.direction(), defaults.sort()
                ))
                .orElse(DEFAULT_PAGE_REQUEST);
    }
}*/
