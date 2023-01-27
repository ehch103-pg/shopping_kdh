package co.kr.shopping.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	@Autowired
	private final MemberMapper memberMapper;
	
	@Transactional
	public int insertMember(MemberVO member) {
		int count = 0;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemberPw(passwordEncoder.encode(member.getPassword()));
		
		count = memberMapper.insertMember(member);
		return count;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO member = memberMapper.selectMemberInfo(username);
		if(member == null)
			throw new UsernameNotFoundException("User not authorized.");
		return member;
	}
}
