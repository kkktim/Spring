package kr.co.ch08.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.co.ch08.service.User1Service;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private User1Service userService;
	
	//인증 설정
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//접근 권한 설정
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/guest/**").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		http.authorizeRequests().antMatchers("/member/**").hasRole("MEMBER");
		
		// User2 테이블에 있는 사용자를 위한 기본 인증 설정(특별한 권한 X)
		http.authorizeRequests().antMatchers("/user/loginSuccess").authenticated();
		
		//사이즈 위조 요청에 대한 방지 설정
		http.csrf().disable();
		
		//로그인 폼 설정(기본 Security 로그인 폼 사용)
		//http.formLogin();
		
		//로그인 폼 설정(사용자 정의 폼 사용)
		http.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/user/loginSuccess")
		.failureUrl("/user/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		//로그아웃 설정(사용자 정의)
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/user/login");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Security 테스트 계정 설정
		// {noop} - 비밀번호를 평문으로 사용 (암호화 하지 않음)
		auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
		
		//로그인 인증 처리 메서드
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
}
