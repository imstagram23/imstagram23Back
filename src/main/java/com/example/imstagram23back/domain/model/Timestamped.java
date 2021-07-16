package com.example.imstagram23back.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 * 2021-07-16 20:40 by 최민서
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate // 최초 생성 시점
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막 변경 시점
    private LocalDateTime modifiedAt;
}
