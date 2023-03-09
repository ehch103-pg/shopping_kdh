package co.kr.shopping.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO{

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
	
	
	
}
