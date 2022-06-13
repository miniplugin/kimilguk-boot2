package com.herokuapp.kimilgukboot2.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.herokuapp.kimilgukboot2.domain.posts.Posts;
import com.herokuapp.kimilgukboot2.domain.posts.PostsRepository;
import com.herokuapp.kimilgukboot2.web.dto.PostsDto;

@AutoConfigureMockMvc
@SpringBootTest
class PostsApiControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private PostsRepository postsRepository;
	@Test
	public void postList() throws Exception {
		mockMvc.perform(
				get("/posts")
				)
		.andExpect(status().isOk())
		.andDo(print());
	}
	@Test
	void save() throws Exception {
		PostsDto requestDto = PostsDto.builder()
				.title("게시물제목")
				.content("게시물내용")
				.author("user")
				.build();
		mockMvc.perform(
				post("/api/posts/save") //메서드 체인닝방식으로 구현합니다.
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(requestDto))
				)
		.andExpect(status().isOk());
		List<Posts> postList = postsRepository.findAll();
		System.out.println("디버그" + postList.toString());
		System.out.println("디버그" + postList.get(0).getCreateDate());
	}

}
