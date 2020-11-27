package com.vastmoon.sparrow.jpa;


import com.vastmoon.sparrow.core.domain.PersistenceObject;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * <p> ClassName: Audit
 * <p> Description: 审计字段
 *
 * @author zhangshuai 2019/12/12
 */

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BasePO implements PersistenceObject {
    protected static final long serialVersionUID = 5788544892374567450L;
    @CreatedBy
    @Column(name = "gmt_create_by")
    protected String createBy;

    @CreatedDate
    @Column(name = "gmt_create_at", updatable = false)
    protected LocalDateTime createAt;
    @LastModifiedBy
    @Column(name = "gmt_modified_by")
    protected String modifiedBy;

    @LastModifiedDate
    @Column(name = "gmt_modified_at")
    protected LocalDateTime modifiedAt;

    @Column(name = "remark")
    protected String remark;
}
