package com.viitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viitor.repository.UserRepository;

@RestController
@RequestMapping(value = "/rest/auth")
public class ApplicationController {

	@Autowired
	UserRepository userReposity;
	
	@GetMapping(value = "/process",params = {"id"})
	public String process(@RequestParam long id) {
		System.out.println("Processing...");
		return "Processing...."+userReposity.getOne(id).toString();
	}
}
