package com.board.study;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.board.study.domain.comment.CommentDTO;
import com.board.study.domain.comment.CommentService;

@SpringBootTest
public class CommentTest {

	@Autowired
	private CommentService commentService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void insertComment() {
		int number = 20;
		
		for(int i = 1; i <= number; i++) {
			CommentDTO params = new CommentDTO();
			params.setPostId((long) 2560);
			params.setContent(i + "번 댓글을 추가합니다");
			params.setWriter(i + "번회원");
			commentService.insertComment(params);
		}
		
		logger.debug("댓글" + number + "개가 등록됐습니다");
	}
	
	@Test
	public void deleteComment() {
		commentService.deleteComment((long) 2);
		getCommentList();
	}
	
	@Test
	public void getCommentList() {
		CommentDTO params = new CommentDTO();
		params.setPostId((long) 2560);
		commentService.selectCommentList(params);
	}
}
