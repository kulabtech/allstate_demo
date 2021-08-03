package com.jwt.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.AuthenticationRequest;
import com.jwt.model.AuthenticationResponse;
import com.jwt.model.EmployeeModel;
import com.jwt.security.JwtUtil;
import com.jwt.security.MyUserDetailsService;

@RestController
public class HelloResources {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtilService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/employee")
	public EmployeeModel hello() {
		
		EmployeeModel employee = new EmployeeModel("vish","pune","28");
		return employee;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
	{
		System.out.println("inside authenticate");
		try {
			
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			System.out.println(e.getMessage());
			throw new Exception("invalid username and password",e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		System.out.println(userDetails.getUsername());
		
		final String jwt = jwtUtilService.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails.getAuthorities()));
	}


}
