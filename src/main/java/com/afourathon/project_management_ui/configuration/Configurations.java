package com.afourathon.project_management_ui.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@EnableWebSecurity
public class Configurations {
	
	@Value("${spring.security.user.name}")
	private String username;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
		    .csrf()
	        .disable()
	    	.authorizeHttpRequests()
	    	.anyRequest().authenticated()
	        .and().formLogin()
	        .loginPage("/login")
	        .defaultSuccessUrl("/displayAllProjects", true)
	        .failureUrl("/login?error").permitAll()
	        .and()
	        .logout()
	        .logoutUrl("/login?logout")
	        .deleteCookies("JSESSIONID").permitAll();
	    return http.build();
	}
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername(username)
            .password(passwordEncoder().encode(password))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// Adding a bean definition to enable HTTP request logging
	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter
		= new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA: ");
		return filter;
	}

}
