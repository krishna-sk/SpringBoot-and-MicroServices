package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("SELECT uname,upwd,uenable FROM users_tab WHERE uname=?")
		.authoritiesByUsernameQuery("SELECT uname,urole FROM users_tab WHERE uname=?")
		.passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/welcome").permitAll()
		.antMatchers("/home").authenticated()
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		.antMatchers("/admin").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/home", true)
		
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied");
	}
}