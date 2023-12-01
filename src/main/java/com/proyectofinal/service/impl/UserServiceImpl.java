package com.proyectofinal.service.impl;

import com.proyectofinal.model.User;
import com.proyectofinal.repository.IUserRepo;
import com.proyectofinal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo userRepo;

    @Override
    public User register(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getOne(int id) {
        Optional<User> op = userRepo.findById(id);
        return op.orElseGet(User :: new);
    }

    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }


    @Override
    public List<User> getSupportUser() {
        return userRepo.getSupport();
    }
}
