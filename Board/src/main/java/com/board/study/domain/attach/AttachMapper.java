package com.board.study.domain.attach;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachMapper {

	/**
	 * 파일 정보를 저장
	 * @param attachList 저장할 정보
	 * @return 성공여부
	 */
	public int insertAttach(List<AttachDTO> attachList);
	
	/**
	 * 파일의 상세정보 조회(다운로드에 필요함)
	 * @param id 파일 번호(PK)
	 * @return 파일정보
	 */
	public AttachDTO selectAttachDetail(Long id);
	
	/**
	 * 파일 삭제
	 * @param postId 파일을 삭제할 게시글 번호
	 * @return 성공여부
	 */
	public int deleteAttach(Long postId);
	
	/**
	 * 파일 목록을 조회
	 * @param postId 게시글 번호
	 * @return
	 */
	public List<AttachDTO> selectAttachList(Long postId);
	
	/**
	 * 특정 게시글에 포함된 파일의 개수를 조회
	 * @param postId 게시글 번호
	 * @return 파일 개수
	 */
	public int selectAttachTotalCount(Long postId);
	
	/**
	 * 파일의 삭제 취소
	 * @param ids - 파일 번호(PK)
	 * @return 성공여부
	 */
	public int undeleteAttacth(List<Long> ids);
}
