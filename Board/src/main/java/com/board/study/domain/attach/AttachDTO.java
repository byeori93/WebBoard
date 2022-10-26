package com.board.study.domain.attach;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO {

	/** 파일 번호(PK) */
	private Long id;
	
	/** 게시글 번호(FK) */
	private Long postId;
	
	/** 원본 파일명 */
	private String originalName;
	
	/** 저장 파일명 */
	private String saveName;
	
	/** 파일 크기 */
	private Long size;
	
	/** 삭제 여부 */
	private int deleteYn;
	
	/** 등록일 */
	private LocalDateTime insertTime;
	
	/** 삭제일 */
	private LocalDateTime deleteTime;
}
