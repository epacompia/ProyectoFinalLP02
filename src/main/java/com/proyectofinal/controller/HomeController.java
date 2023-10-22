package com.proyectofinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/main")
	public String goHome(Model model) {
		model.addAttribute("titulo","Bienvenidos");
		return "Index";
	}
	
	@GetMapping("/")
	public String login() {
		return "signin";
	}
}
