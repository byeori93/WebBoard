package com.board.study.domain.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.study.common.dto.SearchDTO;
import com.board.study.paging.Pagination;
import com.board.study.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostMapper postMapper;
	
	/**
	 * 게시글 저장
	 * @param params - 게시글 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long savePost(final PostRequest params) {
		postMapper.save(params);
		return params.getId();
	}
	
	/**
	 * 게시글 상세정보 조회
	 * @param id - PK
	 * @return 게시글 상세정보
	 */
	public PostResponse findPostById(final Long id) {
		return postMapper.findById(id);
	}
	
	/**
	 * 게시글 수정
	 * @param params - 게시글 정보
	 * @return PK
	 */
	@Transactional
	public Long updatePost(final PostRequest params) {
		postMapper.update(params);
		return params.getId();
	}
	
	/**
	 * 게시글 삭제
	 * @param id - PK
	 * @return PK
	 */
	public Long deletePost(final Long id) {
		postMapper.deleteById(id);
		return id;
	}
	

	/**
	 * 게시글 리스트 조회
	 * @param params - search conditions
	 * @return list and pagination information
	 */
	public PagingResponse<PostResponse> findAllPost(final SearchDTO params) {
		int count = postMapper.count(params);
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		List<PostResponse> list = postMapper.findAll(params);
		return new PagingResponse<>(list, pagination);
	}
}
