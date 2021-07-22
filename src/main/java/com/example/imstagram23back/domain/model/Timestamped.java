package com.example.imstagram23back.domain.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;



@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {
    // 최초 생성 시점
    @CreatedDate
    private LocalDateTime createdAt;

    // 마지막 변경 시점
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
