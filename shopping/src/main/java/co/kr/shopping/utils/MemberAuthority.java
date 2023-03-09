package co.kr.shopping.utils;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public enum MemberAuthority implements GrantedAuthority{
	
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), RETIRE("ROLE_RETIRE");

    private String authority;


	private MemberAuthority(String authority) {
		// TODO Auto-generated constructor stub
		this.authority = authority;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}
}
