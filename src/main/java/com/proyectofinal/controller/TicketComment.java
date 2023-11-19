package com.proyectofinal.controller;

import com.proyectofinal.model.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketComment {



    /*@GetMapping("/ticket/comments/{id}")
    public String getTicketComment(@PathVariable("id")int id, Model model){

        return "ticket-detail";
    }*/

    @GetMapping("/ticket/comments")
    public String getTicketComment(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket-comments";
    }


}
