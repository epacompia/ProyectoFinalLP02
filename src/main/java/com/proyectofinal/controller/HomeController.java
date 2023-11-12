package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectofinal.model.User;
import com.proyectofinal.repository.IUserRepo;

@Controller
public class HomeController {
	@Autowired
	private IUserRepo repoUser;

	@GetMapping("/")
	public String goHome(Model model) {
		model.addAttribute("titulo","Bienvenidos");
		return "Index";
	}
	
	
	
	//login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String saveLogin(
			@RequestParam(name="txtEmail") String email,
			@RequestParam(name="txtPassword") String password,
			Model model) {
		//validacion
		User u=repoUser.findByEmailAndPassword(email, password);
		if(u != null) {
			return "redirect:/ticket";
		}else {
			model.addAttribute("mensaje","Su correo o contraseña no son validos");
			model.addAttribute("cssmensaje","alert alert-danger");
			return "redirect:/login";
		}
		//return "login";
	}
	
	
	
}
