package com.board.study.domain.post;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.board.study.common.dto.SearchDTO;
import com.board.study.domain.attach.AttachDTO;
import com.board.study.domain.attach.AttachMapper;
import com.board.study.paging.Pagination;
import com.board.study.paging.PagingResponse;
import com.board.study.util.FileUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostMapper postMapper;
	private final AttachMapper attachMapper;
	private final FileUtils fileUtils;
	
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
	 * 첨부파일이 포함된 게시글 저장
	 * @param params - 게시글 정보
	 * @param files - 첨부파일 정보
	 * @return 성공여부
	 */
	@Transactional
	public boolean savePost(final PostRequest params, MultipartFile[] files) {
		int queryResult = 0;
		savePost(params);
		queryResult = 1;
		List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getId());
		if (CollectionUtils.isEmpty(fileList) == false) {
			queryResult = attachMapper.insertAttach(fileList);
			if (queryResult < 1) {
				queryResult = 0;
			}
		}
		return (queryResult > 0);
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
	public boolean updatePost(final PostRequest params, final MultipartFile[] files) {
		int queryResult = 0;
		postMapper.update(params);
		queryResult = 1;
		
		//파일이 추가, 삭제, 변경된 경우(변경사항이 있으면 기존 파일은 무조건 삭제해야한다)
		if(params.getChangeYn().equals("Y")) {
			attachMapper.deleteAttach(params.getId());
			
			//추가된 파일 등록
			List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getId());
			if (CollectionUtils.isEmpty(fileList) == false) {
				attachMapper.insertAttach(fileList);
			}

			//fileIds에 포함된 id를 가지는 파일의 삭제여부를 0으로 업데이트(변경된 파일과 겹치는게 있으면 추가 업로드 하지 말고 삭제여부를 변경)
			if (CollectionUtils.isEmpty(params.getFileIds()) == false) {
				attachMapper.undeleteAttacth(params.getFileIds());
			}
		}
		return (queryResult > 0);
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
	
	/**
	 * 첨부파일 리스트 조회
	 * @param postId - 게시글 번호
	 * @return 첨부파일 리스트
	 */
	public List<AttachDTO> getAttachFileList(Long postId) {
		int fileTotalCount = attachMapper.selectAttachTotalCount(postId);
		if (fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return attachMapper.selectAttachList(postId);
	}
}
