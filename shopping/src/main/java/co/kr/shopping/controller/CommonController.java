package co.kr.shopping.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.kr.shopping.service.CommonService;

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
	
}
