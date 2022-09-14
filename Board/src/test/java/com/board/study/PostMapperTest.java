package com.board.study;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.study.common.dto.SearchDTO;
import com.board.study.domain.post.PostMapper;
import com.board.study.domain.post.PostRequest;
import com.board.study.domain.post.PostResponse;

@SpringBootTest
public class PostMapperTest {

	@Autowired
	PostMapper postMapper;
	
	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		params.setNoticeYn(false);
		postMapper.save(params);
		
		SearchDTO page = new SearchDTO();
		List<PostResponse> posts = postMapper.findAll(page);
		System.out.println("전체 게시글 수는 " + posts.size() + "개 입니다.");
	}
	
	@Test
	void findById() {
		PostResponse post = postMapper.findById(1L);
			String title = post.getTitle();
			String content = post.getContent();
			String writer = post.getWriter();
			
			System.out.println("===========================");
			System.out.println("제목 : " + title);
			System.out.println("내용 : " + content);
			System.out.println("작성자 : " + writer);
			System.out.println("===========================");
	}
	
	@Test
	void update() {
		//게시글 수정
		PostRequest params = new PostRequest();
        params.setId(1L);
        params.setTitle("1번 게시글 제목 수정합니다.");
        params.setContent("1번 게시글 내용 수정합니다.");
        params.setWriter("도뎡이");
        params.setNoticeYn(true);
        postMapper.update(params);
        
        //수정한 게시글 확인
		PostResponse post = postMapper.findById(1L);
		String title = post.getTitle();
		String content = post.getContent();
		String writer = post.getWriter();
		
		System.out.println("===========================");
		System.out.println("제목 : " + title);
		System.out.println("내용 : " + content);
		System.out.println("작성자 : " + writer);
		System.out.println("===========================");
	}
	
    @Test
    void delete() {
    	SearchDTO page = new SearchDTO();
        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll(page).size() + "개입니다.");
        postMapper.deleteById(1L);
        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll(page).size() + "개입니다.");
    }
}
