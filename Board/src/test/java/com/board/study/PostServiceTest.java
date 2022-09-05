package com.board.study;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.study.domain.post.PostRequest;
import com.board.study.domain.post.PostResponse;
import com.board.study.domain.post.PostService;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	PostService postService;
	
	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		params.setNoticeYn(false);
		postService.savePost(params);
	}
	
	@Test
	void findPostById() {
		PostResponse post =  postService.findPostById(2L);
		System.out.println("============================");
		System.out.println(post.getTitle());
		System.out.println(post.getContent());
		System.out.println(post.getWriter());
		System.out.println("============================");
		
	}
}
