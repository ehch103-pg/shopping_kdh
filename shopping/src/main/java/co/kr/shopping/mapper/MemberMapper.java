package co.kr.shopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import co.kr.shopping.vo.MemberVO;

@Mapper
public interface MemberMapper  {

	MemberVO selectMemberInfo(String userID);
	
	void insertMember(MemberVO memberVO);
}
