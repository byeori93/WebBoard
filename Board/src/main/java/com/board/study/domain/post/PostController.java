package com.board.study.domain.post;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.study.common.dto.MessageDTO;
import com.board.study.common.dto.SearchDTO;
import com.board.study.paging.PagingResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	//게시글 작성화면
	@GetMapping("/post/write.do")
	public String openPostWriter(@RequestParam(value = "id", required = false) final Long id, Model model) {
		if (id != null) {
			PostResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
		}
		return "post/write";
	}
	
	//신규 게시글 생성
	@PostMapping("/post/save.do")
	public String savePost(final PostRequest params, Model model) {
		postService.savePost(params);
		MessageDTO message = new MessageDTO("게시글 생성이 완료되었습니다", "/post/list.do", RequestMethod.GET, null);
		return showMessageAndRedirect(message, model);
	}
	
	//게시글 업데이트
	@PostMapping("/post/update.do")
	public String updatePost(@RequestParam final Map<String, Object> qeuryParams, final PostRequest params, Model model) {
		postService.updatePost(params);
		MessageDTO message = new MessageDTO("게시글 수정이 완료되었습니다", "/post/list.do", RequestMethod.GET, qeuryParams);
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
		MessageDTO message = new MessageDTO("게시글 삭제가 완료되었습니다", "/post/list.do", RequestMethod.GET, queryParmas);
		return showMessageAndRedirect(message, model);
	}
	
	//사용자에게 메세지를 전달하고 페이지를 리다이렉트
	private String showMessageAndRedirect(final MessageDTO params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
}
