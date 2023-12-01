package com.proyectofinal.controller;

import com.proyectofinal.model.Comment;
import com.proyectofinal.model.Ticket;
import com.proyectofinal.model.User;
import com.proyectofinal.model.UserCustom;
import com.proyectofinal.repository.ITicketRepository;
import com.proyectofinal.service.ICommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TicketComment {


    @Autowired
    private ICommentService commentService;

    @Autowired
    private ITicketRepository ticketRepository;

    /*@GetMapping("/ticket/comments/{id}")
    public String getTicketComment(@PathVariable("id")int id, Model model){

        return "ticket-detail";
    }*/

    @GetMapping("/ticket/comments/{id}")
    public String getTicketComment(@PathVariable("id") Integer id, Model model){

        List<Comment> comments = commentService.getAll(id);
        Ticket ticket = ticketRepository.findById(id).get();


        model.addAttribute("comments", comments);
        model.addAttribute("ticket", ticket);
        model.addAttribute("comment", new Comment());

        return "ticket-comments";

    }

    @PostMapping("/ticket/comments/{id}")
    @Transactional
    public String saveComment(@PathVariable("id") int id, @ModelAttribute("comment") Comment comment, Model model){

        Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCustom userLoggedData = (UserCustom) userLogged;
        User userActive = userLoggedData.getUser();

        Ticket ticket = ticketRepository.findById(id).get();

        Comment newComment = new Comment();
        newComment.setComment_content(comment.getComment_content());
        newComment.setComment_date(LocalDateTime.now());
        newComment.setUser_id(userActive);
        newComment.setTicket_id(ticket);
        commentService.save(newComment);

        return "redirect:/ticket/comments/{id}";

    }

}
