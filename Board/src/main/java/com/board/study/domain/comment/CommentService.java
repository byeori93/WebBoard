package com.board.study.domain.comment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentMapper commentMapper;
	
	/**
	 * 댓글 등록
	 * @param params - 댓글 정보
	 * @return Generated PK
	 */
	@Transactional
	public boolean insertComment(final CommentDTO params) {
		boolean insert = commentMapper.insertComment(params);
		return insert;
	}
	
	/**
	 * 댓글 삭제를 위한 상세내용 조회
	 * @param id
	 * @return 댓글 상세 내용
	 */
	public CommentDTO selectCommentDetail(final Long id) {
		return commentMapper.selectCommentDetail(id);
	}
	
	/**
	 * 댓글 수정
	 * @param params 수정할 내용
	 * @return PK
	 */
	@Transactional
	public boolean updateComment(final CommentDTO params) {
		boolean update = commentMapper.updateComment(params);
		return update;
	}
	
	/**
	 * 댓글 삭제
	 * @param id PK
	 * @return PK
	 */
	@Transactional
	public boolean deleteComment(final Long id) {
		boolean delete = commentMapper.deleteComment(id);
		return delete;
	}
	
	/**
	 * 댓글 목록 조회
	 * @param params 댓글 목록을 가져올 게시글 정보?
	 * @return 댓글 목록
	 */
	public List<CommentDTO> selectCommentList(final CommentDTO params) {
		List<CommentDTO> list = commentMapper.selectCommentList(params);
		return list;
	}
	
	/**
	 * 댓글 갯수
	 * @param params 게시글 정보?
	 * @return 댓글 갯수
	 */
	public int selectCommentTotalCount(final CommentDTO params) {
		return commentMapper.selectCommentTotalCount(params);
	}
}
