package co.kr.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void JoinMember(MemberVO memberVO) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberVO.setMemPw(passwordEncoder.encode(memberVO.getMemPw()));
		memberVO.setChId(memberVO.getMemId());
		memberVO.setRole("USER");
		memberMapper.insertMember(memberVO);
	}
	
	public MemberVO selectMember(String id) {
		MemberVO member = memberMapper.selectMemberInfo(id);
		return member;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO memberVO = memberMapper.selectMemberInfo(username);
		System.err.println(memberVO);
		if(memberVO==null){
            throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
        }
		
		return memberVO;
	}
}
