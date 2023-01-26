package co.kr.shopping.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import co.kr.shopping.dao.UserRepository;
import co.kr.shopping.utils.SecurityUser;
import co.kr.shopping.utils.UserDetailsImpl;
import co.kr.shopping.vo.MemberVO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO member = userRepository.findIdByUsername(username); 
		if(member == null) {
			throw new UsernameNotFoundException("해당하는 사용자가 없습니다.");
		}
		return User.builder()
			.username(member.getUsername())
			.password(member.getPassword())
			.roles(member.getRole().toString())
			.build();
	}
}
