package com.proyectofinal.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyectofinal.model.User;
import com.proyectofinal.repository.IUserRepo;

import jakarta.servlet.http.HttpSession;

public class UserServiceOLD1  implements UserDetailsService{

	@Autowired
	private IUserRepo repoUser;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = repoUser.findByEmail(username);
		
				//imprimiendo en consola el usaurio encontrado
				if(u != null) {
					System.out.println("Usuario encontrado:"+u.getEmail());
				}else {
					System.out.println("Usuario no encontrado:" + username);
				}
		
				//Si en caso u es mull
				if (u == null) {
				    throw new UsernameNotFoundException(String.format("Usuario no existe", username));
				}
				
				// Crear un objeto UserDetails personalizado con propiedades 
			    UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(u.getEmail())
			            .password(u.getPassword())
			            .authorities(getAuthorities(u))
			            .accountExpired(false)
			            .accountLocked(false)
			            .credentialsExpired(false)
			            //.disabled(!u.isEnabled())
			            .build();

			    return userDetails;
				
	}
	
	// Obtener los roles  del usuario (roles)
	private Collection<? extends GrantedAuthority> getAuthorities(User user) {
	    return user.getRol_id().stream()
	            .map(role -> new SimpleGrantedAuthority(role.getName_rol()))
	            .collect(Collectors.toList());

}
}
