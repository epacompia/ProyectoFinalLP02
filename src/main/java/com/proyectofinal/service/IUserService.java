package com.proyectofinal.service;

import com.proyectofinal.model.User;

import java.util.List;

public interface IUserService extends ICRUDService<User>{


    public List<User> getSupportUser();

}
