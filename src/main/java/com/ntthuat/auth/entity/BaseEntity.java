package com.ntthuat.auth.entity;

import org.springframework.data.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * The abstract base super class for all entity classes which has 4 common columns:
 * created_by, created_date, updated_by, updated_date
 */
@MappedSuperclass
public abstract class BaseEntity implements Auditable<String, Long, ZonedDateTime>, Serializable {

    @Column(name="created_by", nullable = false, updatable = false)
    protected String createdBy;

    @Column(name="created_date", nullable = false, updatable = false)
    protected ZonedDateTime createdDate;

    @Column(name="updated_by", nullable = false)
    protected String updatedBy;

    @Column(name="updated_date", nullable = false)
    protected ZonedDateTime updatedDate;

    @Override
    public abstract Long getId();

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<ZonedDateTime> getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    @Override
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(updatedBy);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.updatedBy = lastModifiedBy;
    }

    @Override
    public Optional<ZonedDateTime> getLastModifiedDate() {
        return Optional.ofNullable(updatedDate);
    }

    @Override
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.updatedDate = lastModifiedDate;
    }
}
