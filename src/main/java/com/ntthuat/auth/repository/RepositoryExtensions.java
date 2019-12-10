package com.ntthuat.auth.repository;

import org.springframework.data.domain.Auditable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author ntthuat
 */
@NoRepositoryBean
public interface RepositoryExtensions<E extends Auditable<String, ID, ZonedDateTime>, ID>
        extends PagingAndSortingRepository<E, ID> {

    default E save(E entity, Boolean auditable) {
        final String auditor = "system";
        audit(entity, auditor);
        return this.save(entity);
    }

    default E audit(E entity, String auditor) {
        if (!entity.getCreatedBy().isPresent()) {
            entity.setCreatedBy(auditor);
        }

        if (!entity.getCreatedDate().isPresent()) {
            entity.setCreatedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }

        entity.setLastModifiedBy(auditor);
        entity.setLastModifiedDate(ZonedDateTime.now(ZoneId.systemDefault()));
        return entity;
    }
}
