package com.viitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.viitor.modal.User;
import com.viitor.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		CustomUserDetails custUserDet ;
		
		if(user != null) {
			custUserDet = new CustomUserDetails();
			custUserDet.setUser(user);
			return custUserDet;
		}else {
			throw new UsernameNotFoundException("User not exist with the name");
		}
		
//		return null;
	}

}
