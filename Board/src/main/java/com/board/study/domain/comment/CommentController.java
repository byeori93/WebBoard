package com.board.study.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.board.study.adapter.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping(value = "/comments/{postId}")
	public JsonObject getCommentList(@PathVariable("postId") Long boardId, @ModelAttribute("param") CommentDTO params) {
		JsonObject jsonObj = new JsonObject();
		List<CommentDTO> commentList = commentService.selectCommentList(params);
		if (CollectionUtils.isEmpty(commentList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonarr =gson.toJsonTree(commentList).getAsJsonArray();
			jsonObj.add("commentList", jsonarr);
		}
		return jsonObj;
	}
}
