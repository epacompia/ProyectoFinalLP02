package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private int rol_id;
	
	@Column(name = "name_rol", length = 30, nullable = false, unique = true)
	private String name_rol;
	
	private boolean flag;
	
	
	@Column(name = "create_at", updatable=false)
	private LocalDateTime create_at;
	
	
	@Column(name = "update_at")
	private LocalDateTime update_at;

	
	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public String getName_rol() {
		return name_rol;
	}

	public void setName_rol(String name_rol) {
		this.name_rol = name_rol;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}

	public LocalDateTime getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}
	
	
	
	
}
