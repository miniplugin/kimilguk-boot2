package com.herokuapp.kimilgukboot2.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ManyFile {
	@Id//주키 Primary Key 로 만든다
	@GeneratedValue(strategy=GenerationType.IDENTITY)//자동증가값으로 구현한다 
	private Long id;
	@Column(nullable=false)
	private String origFilename;//한글파일명
	@Column(nullable=false)
	private String filename;//하드디스크에 저장될 파일명
	@Column(nullable=false)
	private String filePath;//저장될 경로
	@ManyToOne
	private Posts posts;
	@Builder
	public ManyFile(Long id, String origFilename, String filename, String filePath, Posts posts) {
		this.id = id;
		this.origFilename = origFilename;
		this.filename = filename;
		this.filePath = filePath;
		this.posts = posts;
	}		
	
}
