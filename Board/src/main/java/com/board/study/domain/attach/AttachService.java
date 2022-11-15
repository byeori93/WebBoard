package com.board.study.domain.attach;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachService {

	private final AttachMapper attachMapper;
	
	/**
	 * 첨부파일 리스트 조회
	 * @param postId - 게시글 번호
	 * @return 첨부파일 리스트
	 */
	public List<AttachDTO> selectAttachFileList(Long postId) {
		int fileTotalCount = attachMapper.selectAttachTotalCount(postId);
		if (fileTotalCount < 1) {
			return Collections.emptyList();
		}
		return attachMapper.selectAttachList(postId);
	}
	
	/**
	 * 첨부파일 다운로드를 위한 상세정보 조회
	 * @param id - 첨부파일 번호
	 * @return 첨부파일 상세정보
	 */
	public AttachDTO selectAttachDetail(Long id) {
		return attachMapper.selectAttachDetail(id);
	}

}
