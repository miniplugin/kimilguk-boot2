package com.herokuapp.kimilgukboot2.service.posts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;
import com.herokuapp.kimilgukboot2.web.dto.PostsDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //멤버변수에 final 키워드가 붙은 변수를 객체로 만드는 기능
@Service
public class PostsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final PostsRepository postsRepository;
	
	//Create
	@Transactional
	public Long save(PostsDto requestDto) {//저장한 이후 게시글 아이디값을 리턴(반환)
		return postsRepository.save(requestDto.toEntity()).getId();		
	}
	//Read One
	@Transactional
	public PostsDto postsOne(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "번호의 게시글이 없습니다."));
		return new PostsDto(entity);
	}
	//Update
	@Transactional
	public Long update(Long id, PostsDto requestDto) {//신규 수정값
		Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "번호의 게시글이 없습니다."));//기존DB 게시글
		post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getFileId());
		return id;
	}
	//Delete
	@Transactional
	public void delete(Long id) {
		Posts entity = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "번호의 게시글이 없습니다."));//에러처리
		postsRepository.delete(entity);
	}
	//Read All
	public Page<Posts> postsList(Pageable pageable) {
		Page<Posts> postsList = postsRepository.findAll(pageable);
		return postsList;
	}
}
