package co.kr.shopping.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.User;

import co.kr.shopping.utils.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "member")
@Entity
public class Member {
	
	@Id @GeneratedValue
	private Long id;
	private String username;
	private String password;
	private boolean enabled;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Builder
	public Member(String username, String password, boolean enabled, Role role) {
		this.username = username;
		this.password = password;
		this.enabled  = enabled;
		this.role     = role;
	}
}
