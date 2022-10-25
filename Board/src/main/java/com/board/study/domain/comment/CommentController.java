package com.board.study.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	//댓글 리스트
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
	
	//댓글 입력&수정
	@RequestMapping(value = {"/comments", "/comments/{id}"}, method = {RequestMethod.POST, RequestMethod.PATCH})
	public JsonObject insertComment(@PathVariable(value = "id", required = false) Long id, @RequestBody final CommentDTO params) {
		JsonObject jsonObj = new JsonObject();
		try {
			if (id != null) {
				params.setId(id);
				boolean isUpdated = commentService.updateComment(params);
				jsonObj.addProperty("result", isUpdated);
			} else {
				boolean isRegistered = commentService.insertComment(params);
				jsonObj.addProperty("result", isRegistered);
			}
			
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에서 문제가 발생하였습니다.");
		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}
		return jsonObj;
	}
	
	//댓글 삭제
	@DeleteMapping(value = "/comments/{id}")
	public JsonObject deleteComment(@PathVariable("id") final Long id) {
		JsonObject jsonObj = new JsonObject();
		try {
			boolean isDeleted = commentService.deleteComment(id);
			jsonObj.addProperty("result", isDeleted);
		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");
		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생했습니다.");
		}
		return jsonObj;
	}
}
