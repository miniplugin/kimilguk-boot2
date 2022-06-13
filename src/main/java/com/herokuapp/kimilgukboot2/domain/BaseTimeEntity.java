package com.herokuapp.kimilgukboot2.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
	@CreatedDate
	private LocalDateTime createDate;//게시판, 회원관리에서 공통으로 사용하는 등록일시
	@LastModifiedDate
	private LocalDateTime modifieDate;
}
