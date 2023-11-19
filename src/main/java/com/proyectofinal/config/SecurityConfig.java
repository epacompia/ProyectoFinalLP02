package com.proyectofinal.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserService_Old userService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http

                .csrf( csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        //.requestMatchers(HttpMethod.GET,  "/login", "/resources/**", "/static/**","css/**","js/**","lib/**","scss/**").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/login", "/resources/**", "/static/**","/css/**","/js/**","/lib/**","/scss/**").permitAll()

                        .anyRequest().authenticated()
                ).formLogin(
                form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/ticket")
                        .permitAll()
                ).logout(  logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll()
                )
                .exceptionHandling( exception -> exception
                        .accessDeniedPage("/login"));


        return http.build();
    }
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth
                .userDetailsService(userService).passwordEncoder(passwordEncoder());
                
    }
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
}
