package com.herokuapp.kimilgukboot2.domain.posts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsRepositoryTests {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PostsRepository postsRepository;

	@AfterEach
	void tearDown() throws Exception {
		//테스트 메서드 실행 후 처리작업
		postsRepository.deleteAll();
	}

	@Test
	void test() {
		postsRepository.save(
				Posts.builder()
				.content("게시글 내용")
				.author("user")
				.title("게시글 제목")
				.build()
				);
		List<Posts> postsList = postsRepository.findAll();
		Posts posts = postsList.get(0);//List 배열 데이터는 순서를 갖고, 0부터 시작한다. == 인덱스(목차)
		//로그 레벨(단계) debug > info > warn > error
		logger.debug("등록된 레코드 수" + postsRepository.count());
		logger.info("디버그:" + posts.toString());
	}

}
