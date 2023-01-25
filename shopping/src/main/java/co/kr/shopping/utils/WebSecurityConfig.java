package co.kr.shopping.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import co.kr.shopping.dao.UserRepository;
import co.kr.shopping.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
			.antMatchers("/home", "/login", "/loginFail", "/member/memberReg").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.defaultSuccessUrl("/home", true)
				.failureForwardUrl("/loginError")
			.and().logout()
			.and().csrf().disable();
		
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().antMatchers("/resources/**");
	}
}
