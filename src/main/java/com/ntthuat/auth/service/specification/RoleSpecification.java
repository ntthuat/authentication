package com.ntthuat.auth.service.specification;

import com.ntthuat.auth.dto.RoleLookupParams;
import com.ntthuat.auth.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author ntthuat
 */
@AllArgsConstructor
public class RoleSpecification implements Specification<Role> {

    final RoleLookupParams params;

    @Override
    public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.conjunction();

        if (!StringUtils.isEmpty(params.getRoleId())) {
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("roleId"), params.getRoleId()));
        }

        if (!StringUtils.isEmpty(params.getRoleName())) {
            predicate.getExpressions().add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("roleName")), params.getRoleName().toLowerCase()));
        }

        return predicate;
    }
}
