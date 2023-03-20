package co.kr.shopping.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import co.kr.shopping.service.CommonService;
import net.minidev.json.JSONObject;

@Controller
public class CommonController {
	
	
	@Autowired
	CommonService commService;
	
	
	@GetMapping("/imgShow")
	public void showImg(@RequestParam(value = "uid") String uid, HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> imgInfo = commService.searchFile(uid, "img");
		String fileNm = (String)imgInfo.getOrDefault("file_origin_name", "");
		String fileroot = (String)imgInfo.get("file_root").toString();
		File file = new File(fileroot);
		
	}
	
	@PostMapping("/UploadFile")
	public void uploadFile(@RequestParam("upload") MultipartFile files, Principal principal
			, HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		String uploader = principal.getName();
		OutputStream output = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		try {
			
			byte[] bytes = files.getBytes();
			Map<String, Object> map = commService.saveFile(files, uploader);
			String filePath = map.get("filePath").toString();
			String fileType = map.get("fileType").toString();
			String fileSeq = map.get("fileSeq").toString();
			String fileCd = map.get("fileCd").toString();
			output = new FileOutputStream(filePath);
			output.write(bytes);
			output.flush();
			printWriter = response.getWriter();
			String callback = request.getParameter("CKEditorFuncNum");
			String fileUrl= commService.searchFile(fileSeq, fileCd).get("file_root").toString();
			printWriter.println("<script type='text/javascript'>"
                    + "window.parent.CKEDITOR.tools.callFunction("
                    + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
                    +"</script>");
			printWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
                if(output != null) { 
                	output.close(); 
                }
                if(printWriter != null) { 
                	printWriter.close(); 
                }
            } catch(IOException e) { 
            	e.printStackTrace(); 
            }
		}
	}
}
