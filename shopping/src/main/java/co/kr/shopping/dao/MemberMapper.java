package co.kr.shopping.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
	
	public int insertMember(@Param("param") Map<String, Object> param);
}
