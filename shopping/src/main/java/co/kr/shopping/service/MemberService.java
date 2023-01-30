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
public class MemberService implements UserDetailsService{
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		// TODO Auto-generated constructor stub
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void save(MemberVO.SaveRequest member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberMapper.save(member.toEntity());
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
