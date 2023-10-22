package com.proyectofinal.service;

import java.util.List;

public interface ICRUDService<T> {

	public T register(T entity);
	public T update(T entity);
	public List<T> getAll();
	public T getOne(int id);
	public void delete(int id);
	
}
