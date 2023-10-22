package com.proyectofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectofinal.model.Category;
import com.proyectofinal.service.ICategoryService;
import com.proyectofinal.service.impl.CategoryServiceImpl;

@Controller
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;
	
	
	@GetMapping("/categories")
	public String categories(Model model) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("categories", categories);
		System.out.println(categories);
		return "category";
	}
	
	
}
