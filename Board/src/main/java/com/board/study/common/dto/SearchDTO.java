package com.board.study.common.dto;

import com.board.study.paging.Pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

	private int page;				//현재 페이지 번호
	private int recordSize;			//페이지당 출력할 데이터 개수
	private int pageSize;			//화면 하단에 출력할 페이지 사이즈
	private String keyword;			//검색어
	private String searchType;		//검색 유형
	private Pagination pagination;
	
	public SearchDTO() {
		this.page = 1;
		this.recordSize = 10;
		this.pageSize = 10;
	}
}
