package com.viitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viitor.modal.User;
import com.viitor.repository.UserRepository;

@RestController
@RequestMapping(value = "/secure/rest")
public class AdminController {

	@Autowired
	UserRepository userReposiory;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	@PostMapping(value = "/admin/add")
	public String addUserByAmin(@RequestBody User user) {
		String pwd = user.getPassword();
		String s = pwdEncoder.encode(pwd);
		user.setPassword(s);
		
		userReposiory.save(user);  //password encryption is required.
		
		return "User added successfully....";
	}
	
	@GetMapping("/process")
	public String process() {
		System.out.println("Processing...");
		return "Processing...."+userReposiory.getOne(1l).getUsername();
	}
}
