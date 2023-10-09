package com.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.model.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer>{

}
