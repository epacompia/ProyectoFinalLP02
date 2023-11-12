package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyectofinal.model.Ticket;
import com.proyectofinal.model.TicketStatus;
import com.proyectofinal.repository.ICategoryRepo;
import com.proyectofinal.repository.ITicketRepository;
import com.proyectofinal.repository.ITicketTypeRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




@Controller
public class TicketController {

	@Autowired
	private ITicketRepository repoTicket;
	@Autowired
	private ICategoryRepo repoCat;
	@Autowired
	private ITicketTypeRepository repoTicketType;
	
	//listar
	@GetMapping("/ticket")
	public String index(Model model) {
		model.addAttribute("lstTickets",repoTicket.findAll());
		return "ticket";
	}
	
	//editar
	@GetMapping("/editTicket/{ticket_id}")
	public String editTicket(@PathVariable Integer ticket_id, Model model) {
		Ticket t=repoTicket.findById(ticket_id).get();
		model.addAttribute("ticket",t);
		//Agregando datos del enum
		model.addAttribute("valuesForTicketStatusEnum",TicketStatus.values());
		//formatearfecha
		/*
		 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		 * String formattedDate = t.getTicket_date_create().format(formatter);
		 * model.addAttribute("formattedDate", formattedDate);
		 */
		//llamado a los combos
		model.addAttribute("lstCategories",repoCat.findAll());
		model.addAttribute("lstTicketTypes",repoTicketType.findAll());
		return "editTicket";
	}
	
	//ELIMINAR
	@GetMapping("/ticket/deleteTicket/{ticket_id}")
	@Transactional
	public String deleteTicket(@PathVariable Integer ticket_id) {
	    repoTicket.updateTicketFlag(ticket_id);
	    return "redirect:/ticket";
	}


}
