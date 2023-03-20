package co.kr.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcWebClientAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.shopping.mapper.MemberMapper;
import co.kr.shopping.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Transactional
	public int JoinorModifyByMember(MemberVO memberVO, int code, String userCheck) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(code == 1) {
			if(userCheck == "A") {
				memberVO.setMemPw(passwordEncoder.encode(memberVO.getMemPw()));
				memberVO.setRole("ROLE_ADMIN");
			}else {
				memberVO.setMemPw(passwordEncoder.encode(memberVO.getMemPw()));
				memberVO.setRole("ROLE_USER");
			}
		    return memberMapper.insertMember(memberVO);
		}else{
			if(!memberVO.getMemPw().equals("N")) {
				memberVO.setMemPw(passwordEncoder.encode(memberVO.getMemPw()));
			}
			
			return memberMapper.updateMember(memberVO);
		}
	}
	
	public MemberVO selectMember(String id) {
		MemberVO member = memberMapper.selectMemberInfo(id);
		return member;
	}

	public int checkMember(String userId) {
		// TODO Auto-generated method stub
		return memberMapper.checkMember(userId);
	}

	public int deleteOrRecoveryMember(String member_Id, String member_Pw
			, MemberVO member, String type) {
		// TODO Auto-generated method stub
		int check = 0;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(passwordEncoder.matches(member_Pw, member.getMemPw())) {
			check = 1;
			if(type.equals("D")) {
				memberMapper.deleteMember(member_Id);
			}else if(type.equals("R")) {
				memberMapper.updateRecoveryMember(member_Id);
			}
		}else {
			check = 0;
		}
		
		return check;
	}

}
