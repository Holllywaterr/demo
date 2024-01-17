package com.thc.sprapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
// 추상 클래스
// 중복되는 필드를 매번 선언하지 않고 상속받아서 자유롭게 사용될 수 있도록
public abstract class AuditingFields {

    @Id
    private String id;

    @Column(nullable = false)
    protected String deleted;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt; // 생성일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime modifiedAt; // 수정일시

    @PrePersist
    public void onPrePersist(){
        String uuid = UUID.randomUUID().toString().replace("-","");
        //uuid = uuid.substring(0,16); // 32자리 짜리를 16개로 줄이는 과정
        this.id = uuid;
        this.deleted = "N";
    }


}
