package com.proyectofinal.controller;

import com.proyectofinal.model.User;
import com.proyectofinal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

	@Autowired
	private IUserService UserService;
	
	
	@GetMapping("/users")
	public String users(Model model) {
		List<User> users = UserService.getAll();
		model.addAttribute("users", users);
		return "user";
	}
	
	
	@GetMapping("/users/new")
	public String saveUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user-form";
	}
	
	@PostMapping("/users/new")
	public String saveUser(@ModelAttribute("user") User user, Model model) {
		User newUser = new User();
		UserService.register(newUser);
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String updateUserForm(@PathVariable Integer id,Model model) {
		
		User user = UserService.getOne(id);
		if(user == null) {
			return "redirect:/users";
		}
		
		model.addAttribute("user", user);
		
		return "user-form";
	}
	
	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable Integer id, @ModelAttribute("User") User user, Model model) {
		User newUser = UserService.getOne(id);
		newUser.setUser_id(user.getUser_id());
		newUser.setFirstname(user.getFirstname());
		newUser.setSecondname(user.getSecondname());
		newUser.setSurname1(user.getSurname1());
		newUser.setSurname2(user.getSurname2());
		newUser.setDocument_type_id(user.getDocument_type_id());
		newUser.setNum_document(user.getNum_document());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setRol_id(user.getRol_id());
		newUser.setFlag(user.isFlag());
		UserService.update(newUser);
		return "redirect:/users";
	}

	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Integer id){
		UserService.delete(id);
		return "redirect:/users";
	}
	
}
