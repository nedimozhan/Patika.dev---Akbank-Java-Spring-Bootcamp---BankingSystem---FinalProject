package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * Login username and password. User cant login if user is in disable situation
	 */
	@PostMapping("/auth")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

			// Control if user is enable or disable
			if (userDetails.isEnabled() == false) {
				return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
			}

			// Generate JWT Token
			final String token = jwtTokenUtil.generateToken(userDetails);
			UserLoginSuccess userLoginSuccess = new UserLoginSuccess(token);

			return new ResponseEntity<>(userLoginSuccess, null, HttpStatus.OK);

		} catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		} catch (DisabledException e) {
			return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
		}
	}
}
