package co.kr.shopping.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.MemberVO;

@Mapper
public interface MemberMapper {
		
	MemberVO selectMemberInfo(@Param("userID") String userID);
	
	int insertMember(MemberVO member);

	int updateMember(@Param("param") Map<String, Object> param);
	
	
}
