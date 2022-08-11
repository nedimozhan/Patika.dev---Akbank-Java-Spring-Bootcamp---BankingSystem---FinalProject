package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.LoginRequest;
import com.ned.finalProject.security.JWTTokenUtil;
import com.ned.finalProject.successresponse.UserLoginSuccess;

@RestController
public class UserLoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/auth")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		} catch (DisabledException e) {
			
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		
		if(userDetails.isEnabled() == false) {
			return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
		}
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		UserLoginSuccess userLoginSuccess = new UserLoginSuccess(token);
		
		return new ResponseEntity<>(userLoginSuccess, null, HttpStatus.OK);
	}
}
