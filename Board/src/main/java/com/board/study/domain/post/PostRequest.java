package com.board.study.domain.post;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
	private Long id;				//PK
	private String title;			//제목
	private String content;			//내용
	private String writer;			//작성자
	private Boolean noticeYn;		//공지글 여부
	private String changeYn;		//파일 변경 여부
	private List<Long> fileIds;		//파일 번호 리스트
}
