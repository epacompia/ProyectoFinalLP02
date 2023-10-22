package com.proyectofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
		return "category";
	}
	
	
	@GetMapping("/categories/new")
	public String saveCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category-form";
	}
	
	@PostMapping("/categories/new")
	public String saveCategory(@ModelAttribute("category") Category category, Model model) {
		Category newCategory = new Category();
		newCategory.setCategory_id(category.getCategory_id());
		newCategory.setName_category(category.getName_category());
		newCategory.setFlag(true);
		categoryService.register(newCategory);
		return "redirect:/categories";
	}
	
	@GetMapping("/categories/edit/{id}")
	public String updateCategoryForm(@PathVariable Integer id,Model model) {
		
		Category category = categoryService.getOne(id);
		if(category == null) {
			return "redirect:/categories";
		}
		
		model.addAttribute("category", category);
		
		return "category-form";
	}
	
	@PostMapping("/categories/edit/{id}")
	public String updateCategory(@PathVariable Integer id, @ModelAttribute("category") Category category, Model model) {
		Category newCategory = categoryService.getOne(id);
		newCategory.setCategory_id(category.getCategory_id());
		newCategory.setName_category(category.getName_category());
		newCategory.setFlag(category.isFlag());
		categoryService.update(newCategory);
		return "redirect:/categories";
	}
	
	
}
