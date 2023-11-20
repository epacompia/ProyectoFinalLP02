package com.proyectofinal.service.impl;

import com.proyectofinal.model.Comment;
import com.proyectofinal.repository.ICommentRepository;
import com.proyectofinal.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAll(int id) {
        return commentRepository.findByTicketId(id);
    }


}
