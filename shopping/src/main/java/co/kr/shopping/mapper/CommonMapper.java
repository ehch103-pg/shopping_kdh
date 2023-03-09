package co.kr.shopping.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonMapper {

	public Map<String, Object> selectFile(@Param("fileSeq") String fileSeq, @Param("fileCd") String fileCd);

	public int saveFile(Map<String, Object> param);
	
	public int modifyFile(Map<String, Object> param);
}
