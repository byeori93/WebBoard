package com.board.study.domain.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
	
	/**
	 * 댓글 등록
	 * @param params 댓글 정보
	 */
	boolean insertComment(CommentDTO params);
	
	/**
	 * 댓글 상세내용 조회
	 * @param id PK
	 * @return 댓글 상세 내용
	 */
	CommentDTO selectCommentDetail(Long id);
	
	/**
	 * 댓글 수정
	 * @param params 수정 내용
	 */
	boolean updateComment(CommentDTO params);
	
	/**
	 * 댓글 삭제
	 * @param id PK
	 * @return 
	 */
	boolean deleteComment(Long id);
	
	/**
	 * 댓글 목록 조회
	 * @param params 게시글 정보
	 * @return 댓글 목록
	 */
	List<CommentDTO> selectCommentList(CommentDTO params);
	
	/**
	 * 게시글에 포함된 댓글 갯수
	 * @param params 게시글 정보
	 * @return 댓글 갯수
	 */
	int selectCommentTotalCount(CommentDTO params);
}
