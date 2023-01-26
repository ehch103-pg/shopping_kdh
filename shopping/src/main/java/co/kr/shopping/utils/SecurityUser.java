package co.kr.shopping.utils;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import co.kr.shopping.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class SecurityUser extends User{
	
	private MemberVO member;
	
	public SecurityUser(MemberVO member) {
		super(  member.getUsername()
			  , member.getPassword()
			  , AuthorityUtils.createAuthorityList(member.getRole().toString()
			  ));
		
		log.info("SecurityUser member.username = {}", member.getUsername());
		log.info("SecurityUser member.password = {}", member.getPassword());
		log.info("SecurityUser member.role = {}", member.getRole().toString());
		
		this.member = member;
	}
	
}
