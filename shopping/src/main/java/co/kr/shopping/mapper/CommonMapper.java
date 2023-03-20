package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.FilesVO;

@Mapper
public interface CommonMapper {

	public Map<String, Object> selectFile(@Param("fileSeq") String fileSeq, @Param("fileCd") String fileCd);

	public String findMaxId(@Param("today") String today);
	
	public void saveFile(FilesVO filesvo);
	
	public void modifyFile(Map<String, Object> param);
	
	public void deleteFile(Map<String, Object> param);
}
