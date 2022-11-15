package com.board.study.domain.attach;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AttachController {
	
	private final AttachService attachService;
	
	//첨부파일 다운로드
	@GetMapping("/post/download.do")
	public void downloadAttachFile(@RequestParam(value = "id", required = false) final Long id, Model model, HttpServletResponse response) {
		AttachDTO fileInfo = attachService.selectAttachDetail(id);
		
		//없는 파일이거나 삭제된 파일의 경우
		if (fileInfo == null || fileInfo.getDeleteYn() == 1) {
			throw new RuntimeException("파일정보를 찾을 수 없습니다");
		}
		
		String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();
		
		String filename = fileInfo.getOriginalName();
		File file = new File(uploadPath, fileInfo.getSaveName());
		
		try {
			byte[] data = FileUtils.readFileToByteArray(file);
			response.setContentType("application/octet-stream");
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
			//전송
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (IOException e) {
			throw new RuntimeException("파일 다운로드에 실패하였습니다.");
		} catch (Exception e) {
			throw new RuntimeException("시스템에 문제가 발생했습니다.");
		}
	}
}
