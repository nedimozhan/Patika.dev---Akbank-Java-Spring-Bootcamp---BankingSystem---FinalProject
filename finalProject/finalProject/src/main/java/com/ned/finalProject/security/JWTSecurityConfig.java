package com.ned.finalProject.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ned.finalProject.enummodel.EAuthorities;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;	
	
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	public JWTSecurityConfig(@Qualifier("UserDetailServiceDatabase") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	@Bean
    public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
    }
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers(HttpMethod.PATCH, "/users/{id}").hasAuthority(EAuthorities.ACTIVATE_DEACTIVATE_USER.toString())
		.antMatchers(HttpMethod.POST, "/accounts").hasAuthority(EAuthorities.CREATE_ACCOUNT.toString())
		.antMatchers(HttpMethod.POST, "/banks").hasAuthority(EAuthorities.CREATE_BANK.toString())
		.antMatchers(HttpMethod.DELETE, "/accounts/{id}").hasAuthority(EAuthorities.REMOVE_ACCOUNT.toString())
		.anyRequest().authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	 @Bean
	  CorsConfigurationSource corsConfigurationSource() 
	  {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:4200/transfer"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT"));
	    configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization","Access-Control-Allow-Origin","Access-Control-Allow-Headers"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	  }
}
