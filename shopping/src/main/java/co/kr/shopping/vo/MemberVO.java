package co.kr.shopping.vo;

import java.beans.Transient;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.kr.shopping.utils.MemberAuthority;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
public class MemberVO {
	
	@Id
	private String Id;
	private String Password;
	private MemberAuthority memberAuthority;
	
	@Builder
	public MemberVO(String Id, String Password, MemberAuthority memberAuthority) {
		this.Id = Id;
		this.Password = Password;
		this.memberAuthority = memberAuthority;
	}
	
	 @Getter
	 @Setter
	 @NoArgsConstructor
	 public static class SaveRequest {
	    private String id;
	    private String password;
	    private MemberAuthority authority;

	    @Transient
	    public MemberVO toEntity() {
	      return MemberVO.builder()
	    		  .Id(this.id)
	    		  .Password(this.password)
	    		  .memberAuthority(this.authority)
	    		  .build();
	        }
	    }
	
}
