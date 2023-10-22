package com.proyectofinal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofinal.model.Category;
import com.proyectofinal.repository.ICategoryRepo;
import com.proyectofinal.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private ICategoryRepo categoryRepo;
	
	@Override
	public Category register(Category entity) {
		// TODO Auto-generated method stub
		return categoryRepo.save(entity);
	}

	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return categoryRepo.save(entity);
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = categoryRepo.findAll();
		return categories;
	}

	@Override
	public Category getOne(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		return category.isPresent() ? category.get() : new Category();
	}

	@Override
	public void delete(int id) {
		categoryRepo.deleteById(id);
	}

}
