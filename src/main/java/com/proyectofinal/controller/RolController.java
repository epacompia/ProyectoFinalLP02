package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectofinal.repository.IRolRepository;

@Controller
public class RolController {

	@Autowired
	private IRolRepository repoRol;
	
	//Cargar listado de roles
	@GetMapping("/rol")
	public String index(Model model) {
		model.addAttribute("lstRoles",repoRol.findAll());
		return "rol";
	}
}
