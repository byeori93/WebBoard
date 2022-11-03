package com.board.study.domain.post;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;

@Getter
public class PostResponse {
	private Long id;					//PK
	private String title;				//제목
	private String content;				//내용
	private String writer;				//작성자
	private int viewCnt;				//조회수
	private Boolean noticeYn;			//공지글 여부
	private Boolean deleteYn;			//삭제여부
	private LocalDateTime createdDate;	//생성일시
	private LocalDateTime modifiedDate;	//최종 수정일시
	private String changeYn;		//파일 변경 여부
	private List<Long> fileIds;		//파일 번호 리스트
}
