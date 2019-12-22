package com.viitor.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StoreSecutiryConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers( "/css/**", "/js/**", "/img/**", "/bootstrap/**");
//	}

	@Override // we can disabled csrf
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.csrf().disable();
		http
		.authorizeRequests()
		.antMatchers("/rest/**", "/upload").permitAll()
		.and()
		.authorizeRequests().antMatchers("/secure/rest/**").permitAll().anyRequest().authenticated()
		.and().httpBasic();
	}

	@Bean // with this annotation we can load BCryptPasswordEncoder bean class into spring
			// context
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * public static void main(String[] args) { Set<String> s = new
	 * HashSet<String>(); s.add("ss"); s.stream().find s.forEach(a->{
	 * System.out.println(a); });
	 * 
	 * List<String> s1 = new ArrayList<String>(); s1.add("ss"); s1.get(1);
	 * 
	 * }
	 */

}
