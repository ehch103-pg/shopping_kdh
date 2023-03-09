package co.kr.shopping.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.kr.shopping.mapper.CommonMapper;

@Service
public class CommonService {
	
	@Autowired
	CommonMapper commonMapper;
	
	public Map<String, Object> searchFile(String Fileseq, String FileCd) {
		return commonMapper.selectFile(Fileseq, FileCd);
	}

	public int saveFile(Map<String, Object> param) {
		String fileroot = "";
		String filename = param.getOrDefault("file_origin_name", "").toString();
		int index = filename.lastIndexOf(".");
		String fileType = filename.substring(index + 1);
		String fileCd = "";
		if(fileType.equals("img") || fileType.equals("png") || fileType.equals("gif")) {
			fileCd = "img";
			fileroot = "C:\\upload\\image";
		}else if(fileType.equals("mp4") || fileType.equals("wav")) {
			fileCd = "video";
			fileroot = "C:\\upload\\video";
		}else {
			fileCd = "etc";
			fileroot = "C:\\upload\\etc";
		}
		File file = new File(fileroot);
		if(!file.exists()) {
			file.mkdir();
		}
		
		param.put("fileOriginName", filename);
		param.put("fileCd", fileCd);
		param.put("fileRoot", fileroot);
		int result = commonMapper.saveFile(param);
		return result;
	}
}
