package com.jwt.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jwt.model.AllStateUser;
@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("bfoere service call");

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<AllStateUser> userModel = restTemplate
				.getForEntity("http://localhost:8081/user/userbyemailid?emailId=" + username, AllStateUser.class);
		
		System.out.println("after service call"+userModel.getBody());

		AllStateUser userDetails = userModel.getBody();

		String roleType = userDetails.getRole();
		System.out.println(roleType+"load user");

		return new User(userDetails.getEmailId(), userDetails.getPassword(), getGrantedAuthority(roleType));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(String role) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (role.equalsIgnoreCase("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;

	}

}
