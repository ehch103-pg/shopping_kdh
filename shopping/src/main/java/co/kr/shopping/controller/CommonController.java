package co.kr.shopping.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	public ResponseEntity<Byte[]> showImg(@RequestParam(value = "imgseq") String imgSeq){
		
		Map<String, Object> imgInfo = commService.searchFile(imgSeq, "img");
		String fileNm = (String)imgInfo.getOrDefault("file_origin_name", "");
		String fileroot = (String)imgInfo.get("file_root").toString();
		File file = new File(fileroot);
		
		
		return new ResponseEntity<>(null);
	}
	
	@PostMapping("/UploadFile")
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request, HttpServletResponse response
			,MultipartHttpServletRequest multiFile , @RequestParam MultipartFile files){
		UUID uid = UUID.randomUUID();
		
		PrintWriter printWriter = null;
		OutputStream outputStream = null;
		
		response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		
		try {
			
			String fileName = files.getOriginalFilename();
			byte[] bytes = files.getBytes();
			String filePath = "";
			String fileCd = FilenameUtils.getExtension(fileName);
			if(fileCd.equals("jpg") || fileCd.equals("gif") || fileCd.equals("png")) {
				filePath = "C://upload//image";
			}else if(fileCd.equals("mp4") || fileCd.equals("avi") || fileCd.equals("wav")) {
				filePath = "C://upload//video";
			}else {
				filePath = "C://upload//etc";
			}
			String ckUploadPath = filePath + uid + "_" + fileName;
			File file = new File(filePath);
			
			if(!file.exists()) {
				file.mkdir();
			}
			outputStream = new FileOutputStream(new File(ckUploadPath));
			outputStream.write(bytes);
			outputStream.flush();
			
			String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = "";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
