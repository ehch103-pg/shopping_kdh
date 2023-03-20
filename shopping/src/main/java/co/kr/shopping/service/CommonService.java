package co.kr.shopping.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.kr.shopping.mapper.CommonMapper;
import co.kr.shopping.vo.FilesVO;

@Service
public class CommonService {
	
	@Autowired
	CommonMapper commonMapper;
	
	public Map<String, Object> searchFile(String Fileseq, String FileCd) {
		return commonMapper.selectFile(Fileseq, FileCd);
	}
	
	public Map<String, Object> saveFile(MultipartFile files, String uploader) throws IllegalStateException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String fileSeq = commonMapper.findMaxId(today);
		String fileName = files.getOriginalFilename();
		
		String filePath ="", fileCd;
		String fileType = FilenameUtils.getExtension(fileName);
		if(fileType.equals("jpg") || fileType.equals("gif") || fileType.equals("png")) {
			filePath = "C:\\upload\\image"+File.separator+fileSeq+"."+fileType;
			fileCd = "image";
		}else if(fileType.equals("avi") || fileType.equals("wav") || fileType.equals("mp4")) {
			filePath = "C:\\upload\\video";
			fileCd = "video";
		}else {
			filePath = "C:\\upload\\etc";
			fileCd = "etc";
		}
		
		filePath = filePath+File.separator+fileSeq+"."+fileType;
		
		File fileDir = new File(filePath);
		if(!fileDir.exists()) {
			fileDir.mkdir();
		}
		
		File saveFile = new File(filePath, fileName);
		files.transferTo(saveFile);
		
		Map<String, Object> map = new HashMap<String, Object>();
		FilesVO filesvo = new FilesVO();
		filesvo.setFileSeq(fileSeq);
		filesvo.setFileCd(fileCd);
		filesvo.setFileOriginName(fileName);
		filesvo.setFileRoot(filePath);
		filesvo.setRegUser(uploader);
		
		commonMapper.saveFile(filesvo);
		map.put("filePath", filePath);
		map.put("fileSeq", fileSeq);
		map.put("fileType", fileType);
		map.put("fileCd", fileCd);
		return map;
	}
}
