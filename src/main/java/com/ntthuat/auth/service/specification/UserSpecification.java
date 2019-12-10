package com.ntthuat.auth.service.specification;

import com.ntthuat.auth.dto.UserLookupParams;
import com.ntthuat.auth.entity.Role;
import com.ntthuat.auth.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

/**
 * @author ntthuat
 */
@AllArgsConstructor
public class UserSpecification implements Specification<User> {

    final UserLookupParams params;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        if (!StringUtils.isEmpty(params.getEmail())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), params.getEmail().toLowerCase()));
        }

        if (!StringUtils.isEmpty(params.getFirstName())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("firstName")), params.getFirstName().toLowerCase()));
        }

        if (!StringUtils.isEmpty(params.getLastName())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("lastName")), params.getLastName().toLowerCase()));
        }

        if (!StringUtils.isEmpty(params.getUserName())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("userName")), params.getUserName().toLowerCase()));
        }

        if (!StringUtils.isEmpty(params.getRole())) {
            Join<User, Role> join = root.join("roles", JoinType.INNER);
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(join.get("roleName")), params.getRole().toLowerCase()));
        }

        if (!StringUtils.isEmpty(params.getSsoId())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("getssoId")), params.getUserName().toLowerCase()));
        }

        return predicate;
    }
}
