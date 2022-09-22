package com.board.study.domain.comment;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
	
	private Long id;						//댓글 테이블의 PK
	private Long postId;					//댓글 테이블의 FK (게시글 테이블의 ID 참조)
	private String content;					//내용
	private String writer;					//작성자
	private Character deleteYn;				//삭제 여부
	private LocalDateTime insertTime;		//등록일
	private LocalDateTime updateTime;		//수정일
	private LocalDateTime deleteTime;		//삭제일
}
