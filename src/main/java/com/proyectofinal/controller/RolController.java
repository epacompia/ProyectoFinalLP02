package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectofinal.model.Rol;
import com.proyectofinal.repository.IRolRepository;

@Controller
public class RolController {

	@Autowired
	private IRolRepository repoRol;
	
	//LISTADO
	@GetMapping("/rol")
	public String index(Model model) {
		model.addAttribute("lstRoles",repoRol.findAll());
		return "rol";
	}
	
	//EDIT
	@GetMapping("/edit/{rol_id}")
	public String edit(@PathVariable Integer rol_id, Model model) {
		Rol r=repoRol.findById(rol_id).get();
		model.addAttribute("rol",r);
		return "editrol";
	}
	
	
	@PostMapping("/saverol")
	public String saverol(@ModelAttribute Rol rol, Model model) {
		try {
			repoRol.save(rol);
			model.addAttribute("mensaje","Registro ok");
			model.addAttribute("cssmensaje","alert alert-primary");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("mensaje","Error al registrar");
			model.addAttribute("cssmensaje","alert alert-danger");
		}
		
		return "rol";
	}
	
	
}
