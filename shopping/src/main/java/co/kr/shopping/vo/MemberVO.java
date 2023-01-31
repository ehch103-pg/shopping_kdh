package co.kr.shopping.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{
	private int memSeq;
	private String memId;
	private String memPw;
	private String memEmail;
	private String memName;
	private String regDate;
	private String memGen;
	private String role;
	private String chId;
	private String delYn;
	private String delTime;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(this.role));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.memPw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.memId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
