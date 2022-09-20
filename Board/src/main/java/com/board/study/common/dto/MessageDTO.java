package com.board.study.common.dto;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 화면에서 메세지를 띄우기 위한 DTO
 * @member message : 사용자에게 전달할 메시지
 * @member redirectUri : 리다이렉트 할 URI
 * @member method : HTTP 요청 메소드(GET, POST)
 * @member data : 화면으로 전달할 파라미터를 저장한 Map 객체(화면 전환간에 이전 페이지 정보를 유지하기 위해서 보내온 파라미터를 저장했다)
 * @author Administrator
 */
@Getter
@AllArgsConstructor
public class MessageDTO {
	
	private String message;				//사용자에게 전달할 메시지
	private String redirectUri;			//리다이렉트할 URI
	private RequestMethod method;		//HTTP 요청 메소드
	private Map<String, Object> data;	//화면으로 전달할 데이터(파라미터)
}
