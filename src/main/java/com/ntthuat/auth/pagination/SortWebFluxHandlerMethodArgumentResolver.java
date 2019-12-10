/*
package com.ntthuat.auth.pagination;

import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Optionals;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


*/
/**
 *
 * @author ntthuat
 *//*

@Component
@AllArgsConstructor
public class SortWebFluxHandlerMethodArgumentResolver
        extends SortHandlerMethodArgumentResolver
        implements HandlerMethodArgumentResolver {

    static final String DEFAULT_PROPERTY_DELIMITER = ",";

    static final String SORT_DEFAULTS_NAME = SortDefault.SortDefaults.class.getSimpleName();
    static final String SORT_DEFAULT_NAME = SortDefault.class.getSimpleName();

    @Override
    public Object resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        List<String> directionParameter = params.get(getSortParameter(parameter));

        SortDefault[] sortDefaults = new SortDefault[]{};

        if (directionParameter == null || directionParameter.isEmpty()) {
            // No parameter
            sortDefaults = getDefault(parameter).orElse(sortDefaults);
        } else if (directionParameter.size() == 1 && !StringUtils.hasText(directionParameter.get(0))) {
            // Single empty parameter, e.g "sort="
            sortDefaults = getDefault(parameter).orElse(sortDefaults);
        } else {
            List<Sort.Order> orders = directionParameter.stream()
                    .map(this::order)
                    .collect(Collectors.toList());
            return Sort.by(orders);
        }

        if (sortDefaults.length == 0) {
            Sort.unsorted();
        }

        return Arrays.stream(sortDefaults)
                .map(def -> Sort.by(
                        def.direction(),
                        def.sort().length > 0 ? def.sort() : def.value()
                ))
                .reduce(Sort::and)
                .get();
    }

    Optional<SortDefault[]> getDefault(MethodParameter parameter) {
        SortDefault.SortDefaults annotatedDefaults = parameter.getParameterAnnotation(SortDefault.SortDefaults.class);
        SortDefault annotatedDefault = parameter.getParameterAnnotation(SortDefault.class);

        if (annotatedDefault != null && annotatedDefaults != null) {
            throw new IllegalArgumentException(
                    String.format("Cannot use both @%s and @%s on parameter %s! Move %s into %s to define sorting order!",
                            SORT_DEFAULTS_NAME, SORT_DEFAULT_NAME, parameter.toString(), SORT_DEFAULT_NAME, SORT_DEFAULTS_NAME
                    )
            );
        }

        return Optionals.firstNonEmpty(
                () -> Optional.ofNullable(annotatedDefault).map(d -> new SortDefault[]{d}),
                () -> Optional.ofNullable(annotatedDefaults).map(SortDefault.SortDefaults::value)
        );
    }

    Sort.Order order(String param) {
        String[] parts = param.split(DEFAULT_PROPERTY_DELIMITER);

        if (parts.length == 1) {
            return Sort.Order.by(parts[0]);
        }

        return Sort.Direction.fromOptionalString(parts[0])
                .map(direction -> new Sort.Order(direction, parts[1]))
                .orElse(Sort.Order.by(parts[0]));
    }
}
*/
