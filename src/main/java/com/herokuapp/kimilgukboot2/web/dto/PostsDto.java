package com.herokuapp.kimilgukboot2.web.dto;

import java.time.LocalDateTime;

import com.herokuapp.kimilgukboot2.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //출력
@Setter //입력
@NoArgsConstructor //생성자 값을 초기화하는 메서드를 자동생성
public class PostsDto {
	private long id;
	private String title;
	private String content;
	private String author;
	private Long fileId;
	private LocalDateTime createDate;
	private LocalDateTime modifieDate;
	
	public PostsDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
		this.fileId = entity.getFileId();
		this.createDate = entity.getCreateDate();
		this.modifieDate = entity.getModifieDate();
	}
	@Builder
	public PostsDto(long id, String title, String content, String author, Long fileId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.fileId = fileId;
	}
	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.fileId(fileId)
				.build();		
	}
}
