package com.hyeyeong.woorim.common.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BasicEntity {

    @Column(name = "createdBy")
    private String createdBy;

    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updatedBy")
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;


}
