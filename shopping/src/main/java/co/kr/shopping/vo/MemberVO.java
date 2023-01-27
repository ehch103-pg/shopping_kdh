package co.kr.shopping.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails {
	private int memberSeq;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String memberRegDate;
	private String memberName;
	private String memberGen;
	private String memberRole;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(this.getMemberRole()));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.memberPw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.memberId;
	}
	
	public String getName() {
		return this.memberName;
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
