package com.herokuapp.kimilgukboot2.web.dto;

import java.time.LocalDateTime;

import com.herokuapp.kimilgukboot2.domain.posts.File;
import com.herokuapp.kimilgukboot2.domain.posts.ManyFile;
import com.herokuapp.kimilgukboot2.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //출력
@Setter //입력
@NoArgsConstructor //생성자 값을 초기화하는 메서드를 자동생성
public class ManyFileDto {
	private Long id;
	private String origFilename;
	private String filename;
	private String filePath;
	private Posts posts;
	
	@Builder
	public ManyFileDto(Long id, String origFilename, String filename, String filePath,Posts posts) {
		this.id = id;
		this.origFilename = origFilename;
		this.filename = filename;
		this.filePath = filePath;
		this.posts = posts;
	}
	
	public ManyFile toEntity() {
		return ManyFile.builder()
				.id(id)
				.origFilename(origFilename)
				.filename(filename)
				.filePath(filePath)
				.posts(posts)
				.build();
	}
}
