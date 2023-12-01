package com.proyectofinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int category_id;
	
	@Column(name = "name_category")
	private String name_category;
	
	private boolean flag;
	
	
	

	public Category() {
	}

	
	
	
	public Category(int category_id, String name_category, boolean flag) {
		this.category_id = category_id;
		this.name_category = name_category;
		this.flag = flag;
	}


	


	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", name_category=" + name_category + ", flag=" + flag + "]";
	}




	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName_category() {
		return name_category;
	}

	public void setName_category(String name_category) {
		this.name_category = name_category;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
	
}
