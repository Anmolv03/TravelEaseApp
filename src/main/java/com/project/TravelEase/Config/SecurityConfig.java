package com.project.TravelEase.Config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomAuthenticationSucessHandler successHandler;
     
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
	return new BCryptPasswordEncoder(12);
}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(customizer->customizer.disable())
		    .authorizeHttpRequests(auth->auth
		    		.requestMatchers( "/users/register", "/users/login", "/users/contact-us").permitAll()
		    		.requestMatchers("/**admin/**","/users/adminDashboard").hasRole("ADMIN")
		    		.anyRequest().authenticated()
		    
		    	)
		    .formLogin(login->login
		    		.loginPage("/users/login")
		    		.successHandler(successHandler)
		    		)
		    .logout(logout->logout
		    		.logoutUrl("/logout")
		    		.logoutSuccessUrl("/users/login")
		    		.permitAll()
		    		);
		              
		return http.build();    
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);
		auth.setPasswordEncoder(getPasswordEncoder());
		return auth;
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)  throws Exception{
		return auth.getAuthenticationManager();
		
	}
	
	
}
