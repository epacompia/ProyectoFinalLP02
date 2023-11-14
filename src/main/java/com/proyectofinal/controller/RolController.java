package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	
	//NUEVO
	@GetMapping("/newrol")
	public String createRol(Model model) {
		Rol r=new Rol();
		model.addAttribute("rol",r);
		return "newrol";
	}
	
	@PostMapping("savenewrol")
	public String createRol(@ModelAttribute Rol rol, Model model) {
		 if (rol.getName_rol() == null || rol.getName_rol().isEmpty()) {
		        model.addAttribute("mensaje1", "El campo 'nombre' es obligatorio");
		        model.addAttribute("cssmensaje1", "alert alert-danger");
		        return "newrol";  
		    }
		try {
			repoRol.save(rol);
			 model.addAttribute("mensaje", "Registro creado con éxito");
		     model.addAttribute("cssmensaje", "alert alert-primary");
		} catch (Exception e) {
			// TODO: handle exception
			 model.addAttribute("mensaje", "Error al crear el registro");
		     model.addAttribute("cssmensaje", "alert alert-danger");
		}
		return "redirect:/rol";
	}	
	
	
	//EDIT
	@GetMapping("/edit/{rol_id}")
	public String edit(@PathVariable Integer rol_id, Model model) {
		Rol r=repoRol.findById(rol_id).get();
		model.addAttribute("rol",r);
		//System.out.println(rol);	
		return "editrol";
	}
	
	
	@PostMapping("/saverol")
	public String saverol(@ModelAttribute Rol rol, Model model) {
		if (rol.getName_rol() == null || rol.getName_rol().isEmpty()) {
	        model.addAttribute("mensaje1", "El campo 'nombre' es obligatorio");
	        model.addAttribute("cssmensaje1", "alert alert-danger");
	        return "newrol";  // Reemplaza 'tu_vista' por el nombre de tu vista
	        //test de prueba
	    }
		  try { repoRol.save(rol); model.addAttribute("mensaje","Registro ok");
		  model.addAttribute("cssmensaje","alert alert-primary"); } catch (Exception e)
		  { // TODO: handle exception
		  model.addAttribute("mensaje","Error al registrar");
		  model.addAttribute("cssmensaje","alert alert-danger");
		  }	 
			
		  return "redirect:/rol";
	}
	
	//ELIMINAR
	@GetMapping("/rol/delete/{rol_id}")
	public String deleteRol(@PathVariable Integer rol_id){
		//repoRol.delete(rol_id);
		repoRol.deleteById(rol_id);
		return "redirect:/rol";
	}
	
	
}
