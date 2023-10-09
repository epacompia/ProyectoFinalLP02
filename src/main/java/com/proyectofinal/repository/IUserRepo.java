package com.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.model.User;

public interface IUserRepo extends JpaRepository<User, Integer>{

}
