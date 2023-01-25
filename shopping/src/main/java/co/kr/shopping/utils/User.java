package co.kr.shopping.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long Id;
	
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	
	private int active;
	private String role = "";
	private String permissions = "";
	
	public User(String username, String password, String role, String permissions) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.permissions = permissions;
		
		this.active = 1;
	}
	
	protected User() {
		
	}
	
	public List<String> getRoleList(){
		if(this.role.length() > 0) {
			return Arrays.asList(this.role.split(","));
		}
		return new ArrayList<>();
	}
	
	public List<String> getPermissionList(){
		if(this.permissions.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<>();
	}
}
