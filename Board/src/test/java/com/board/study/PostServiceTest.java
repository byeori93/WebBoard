package com.board.study;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.board.study.domain.attach.AttachDTO;
import com.board.study.domain.attach.AttachService;
import com.board.study.domain.post.PostRequest;
import com.board.study.domain.post.PostResponse;
import com.board.study.domain.post.PostService;

@SpringBootTest
public class PostServiceTest {

	@Autowired
	PostService postService;
	@Autowired
	AttachService attachService;
	
	@Test
	void save() {
		PostRequest params = new PostRequest();
		params.setTitle("파일첨부 테스트");
		params.setContent("파일첨부 테스트");
		params.setWriter("벼리");
		params.setNoticeYn(false);
		MultipartFile[] files = new MultipartFile[1];
		postService.savePost(params, files);
	}
	
	@Test
	void findPostById() {
		PostResponse post =  postService.findPostById(2L);
		System.out.println("============================");
		System.out.println(post.getTitle());
		System.out.println(post.getContent());
		System.out.println(post.getWriter());
		System.out.println("============================");
		
	}
	
	@Test
	void downloadAttachFile() {
		long id = 12;
		AttachDTO fileInfo = attachService.selectAttachDetail(id);
		
		//없는 파일이거나 삭제된 파일의 경우
		if (fileInfo == null || fileInfo.getDeleteYn() == 1) {
			throw new RuntimeException("파일정보를 찾을 수 없습니다");
		}
		
		String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();
		
		//String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath, fileInfo.getSaveName());
		
		try {
			System.out.println(file.getName());
			byte[] data = FileUtils.readFileToByteArray(file);
			System.out.println(data.length);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException("시스템에 문제가 발생했습니다.");
		}
	}

}
