package com.board.study.domain.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String savePost(final PostRequest params) {
		postService.savePost(params);
		return "redirect:/post/list.do";
	}
	
	//게시글 업데이트
	@PostMapping("/post/update.do")
	public String updatePost(final PostRequest params) {
		postService.updatePost(params);
		return "redirect:/post/list.do";
	}
	
	//게시글 리스트
	@GetMapping("/post/list.do")
	public String openPostList(Model model) {
		List<PostResponse> posts = postService.findAllPost();
		model.addAttribute("posts", posts);
		return "post/list";
	}
	
	//게시글 상세정보 조회
	@GetMapping("/post/view.do")
	public String openPostView(@RequestParam final Long id, Model model) {
		PostResponse post = postService.findPostById(id);
		model.addAttribute("post", post);
		return "/post/view";
	}
}
