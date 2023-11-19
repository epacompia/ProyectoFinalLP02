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
import org.springframework.stereotype.Service;

import com.proyectofinal.model.Rol;
import com.proyectofinal.model.User;
import com.proyectofinal.repository.IUserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
public class UserService  implements UserDetailsService{




	@Autowired
	private IUserRepo repoUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u = repoUser.findByEmail(username);


		//Si en caso u es mull
		if (u == null) {
		    throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}

		List<GrantedAuthority> roles = new ArrayList<>();

		u.getRol_id().forEach(role -> {
			roles.add(new SimpleGrantedAuthority(role.getName_rol()));
		});
		if (u != null) {
	        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), roles);
	    }
		throw new UsernameNotFoundException(String.format("Usuario no existe", username));
	}
	
	
	
	

	


}
