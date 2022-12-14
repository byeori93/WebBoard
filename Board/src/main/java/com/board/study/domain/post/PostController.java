package com.board.study.domain.post;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.study.common.dto.MessageDTO;
import com.board.study.common.dto.SearchDTO;
import com.board.study.domain.attach.AttachDTO;
import com.board.study.domain.attach.AttachService;
import com.board.study.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	private final AttachService attachService;
	
	//게시글 작성화면
	@GetMapping("/post/write.do")
	public String openPostWriter(@RequestParam(value = "id", required = false) final Long id, Model model) {
		if (id != null) {
			PostResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
			List<AttachDTO> fileList = attachService.selectAttachFileList(id);
			model.addAttribute("fileList", fileList);
		}
		return "post/write";
	}
	
	//신규 게시글 생성
	@PostMapping("/post/save.do")
	public String savePost(final PostRequest params, final MultipartFile[] files, Model model) {
		postService.savePost(params, files);
		MessageDTO message = new MessageDTO("게시글 생성이 완료되었습니다", "/post/list.do", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}
	
	//게시글 업데이트
	@PostMapping("/post/update.do")
	public String updatePost(@RequestParam final Map<String, Object> queryParams, final PostRequest params, final MultipartFile[] files, Model model) {
		postService.updatePost(params, files);
		deleteQueryInfo(queryParams);
		MessageDTO message = new MessageDTO("게시글 수정이 완료되었습니다", "/post/list.do", RequestMethod.GET, queryParams);
		return showMessageAndRedirect(message, model);
	}
	
	//게시글 리스트
	@GetMapping("/post/list.do")
	public String openPostList(@ModelAttribute("params") final SearchDTO params, Model model) {
		PagingResponse<PostResponse> response = postService.findAllPost(params);
		model.addAttribute("response", response);
		return "post/list";
	}
	
	//게시글 상세정보 조회
	@GetMapping("/post/view.do")
	public String openPostView(@RequestParam final Long id, Model model) {
		//게시글 번호가 삭제된 경우
		boolean deleteYn = postService.findPostById(id).getDeleteYn();
		if (deleteYn) {
			MessageDTO message = new MessageDTO("올바르지 않은 접근입니다", "list.do", RequestMethod.GET, null);
			return showMessageAndRedirect(message, model);
		}
		
		//첨부파일
		List<AttachDTO> fileList = attachService.selectAttachFileList(id);
		model.addAttribute("fileList",fileList);
		
		PostResponse post = postService.findPostById(id);
		model.addAttribute("post", post);
		return "/post/view";
	}
	
	//게시글 삭제
	@PostMapping("/post/delete.do")
	public String deletePost(@RequestParam final Long id,
							 @RequestParam final Map<String, Object> queryParmas,
							 Model model) {
		postService.deletePost(id);
		deleteQueryInfo(queryParmas);
		MessageDTO message = new MessageDTO("게시글 삭제가 완료되었습니다", "/post/list.do", RequestMethod.GET, queryParmas);
		return showMessageAndRedirect(message, model);
	}
	
	//사용자에게 메세지를 전달하고 페이지를 리다이렉트
	private String showMessageAndRedirect(final MessageDTO params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}

	//queryParams에서 필요 없는 정보 삭제
	private void deleteQueryInfo(Map<String, Object> queryParams) {
		queryParams.remove("noticeYn");
		queryParams.remove("changeYn");
		queryParams.remove("fileIds");
		queryParams.remove("id");
		queryParams.remove("writer");
		queryParams.remove("title");
		queryParams.remove("content");
	}
}
