package com.board.study;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

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
		params.setTitle("파일첨부 테스트");
		params.setContent("파일첨부 테스트");
		params.setWriter("벼리");
		params.setNoticeYn(false);
		MultipartFile[] files = new MultipartFile[1];
		postService.savePost(params, files);
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
