package co.kr.shopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import co.kr.shopping.vo.MemberVO;

@Mapper
public interface MemberMapper  {

	MemberVO selectMemberInfo(@Param("userID") String userID);
	
	int updateMember(MemberVO memberVO);
	
	int insertMember(MemberVO memberVO);

	int checkMember(@Param("userID") String userId);

	int deleteMember(@Param("userID") String userID);

	int updateRecoveryMember(@Param("userID") String userID);

}
