package co.kr.shopping.utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import co.kr.shopping.mapper.MemberMapper;
import co.kr.shopping.vo.MemberVO;

@Component
public class userDetailServiceImpl implements UserDetailsService{

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO memberVO = memberMapper.selectMemberInfo(username);
		SecurityUser securityUser = new SecurityUser();
		if(memberVO != null) {
			securityUser.setNo(memberVO.getMemSeq());
			securityUser.setUsername(memberVO.getMemId());
			securityUser.setPassword(memberVO.getMemPw());
			securityUser.setName(memberVO.getMemName());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(memberVO.getRole())));
		}
		
		return securityUser;
	}

}
