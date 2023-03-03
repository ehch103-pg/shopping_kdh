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
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Transactional
	public int JoinorModifyByMember(MemberVO memberVO, int code, String userCheck) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberVO.setMemPw(passwordEncoder.encode(memberVO.getMemPw()));
		memberVO.setChId(memberVO.getMemId());
		if(code == 1) {
			if(userCheck == "A") {
				memberVO.setRole("ADMIN");
			}else {
				memberVO.setRole("USER");
			}
		    return memberMapper.insertMember(memberVO);
		}else {
			return memberMapper.updateMember(memberVO);
		}
	}
	
	public MemberVO selectMember(String id) {
		MemberVO member = memberMapper.selectMemberInfo(id);
		return member;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO memberVO = memberMapper.selectMemberInfo(username);
		if(memberVO==null){
            throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
        }
		
		return memberVO;
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
