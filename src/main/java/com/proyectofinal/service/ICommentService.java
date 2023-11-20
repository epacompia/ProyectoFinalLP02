package com.proyectofinal.service;

import com.proyectofinal.model.Comment;

import java.util.List;

public interface ICommentService {

    public Comment save(Comment comment);
    public void delete(int id);

    public List<Comment> getAll(int id);

}
