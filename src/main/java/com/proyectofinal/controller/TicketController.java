package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectofinal.config.UserService;
import com.proyectofinal.model.Ticket;
import com.proyectofinal.model.TicketStatus;
import com.proyectofinal.model.UserCustom;
import com.proyectofinal.repository.ICategoryRepo;
import com.proyectofinal.repository.ITicketRepository;
import com.proyectofinal.repository.ITicketTypeRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class TicketController {

	@Autowired
	private ITicketRepository repoTicket;
	@Autowired
	private ICategoryRepo repoCat;
	@Autowired
	private ITicketTypeRepository repoTicketType;
	@Autowired
	private UserService userService;
	
	
	//listar
	@GetMapping("/ticket")
	public String index(Model model) {
		//1. Pasando el usuario de sesion a mi vista para ticket
		// Obtener el usuario autenticado
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    // Obtener el UserDetails y hacer casting
	    UserDetails userDetails = userService.loadUserByUsername(username);
	    User user = (User) userDetails;
	    model.addAttribute("user", user);
	    
		model.addAttribute("lstTickets",repoTicket.findAll());
		return "ticket";
	}
	
	//CREAR TICKET
	@GetMapping("/newticket")
	public String createTicket(Model model) {
		Ticket t=new Ticket();
		model.addAttribute("ticket",t);
		//Agregando datos del enum
		model.addAttribute("valuesForTicketStatusEnum",TicketStatus.values());
		//llamado a los combos
		model.addAttribute("lstCategories",repoCat.findAll());
		model.addAttribute("lstTicketTypes",repoTicketType.findAll());
		return "newTicket";
	}
	
	@PostMapping("/savenewticket")
	public String saveNewTicket(@ModelAttribute Ticket ticket, Model model) {
		System.out.println(ticket);
		
		// Obtener el usuario autenticado y agregar el user_id al modelo
		
		Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCustom userLoggedData = (UserCustom) userLogged;
        com.proyectofinal.model.User userActive = userLoggedData.getUser();
	    
		
		Ticket obj=new Ticket();
		obj.setTicket_id(ticket.getTicket_id());
		obj.setTicket_date_create(LocalDateTime.now());
		obj.setTicket_description(ticket.getTicket_description());
		obj.setTicket_status(ticket.getTicket_status());
		obj.setTicket_title(ticket.getTicket_title());
		obj.setCategory_id(ticket.getCategory_id());
		obj.setIncident_user(userActive); //usuario de sesion
		obj.setTicket_type_id(ticket.getTicket_type_id());
		//obj.setAssigned_date(ticket.getAssigned_date());
		//obj.setAssigned_user(ticket.getAssigned_user());
		obj.setFlag(true);
		repoTicket.save(obj);
		return "redirect:/ticket";
		 
		 
		//return "ticket";
	}
	
	
	//editar
	@GetMapping("/editTicket/{ticket_id}")
//	@PreAuthorize("isAuthenticated()")
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
		
		// Obtener el usuario autenticado y agregar el user_id al modelo
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    model.addAttribute("incident_user", userDetails.getUsername());
		
		//llamado a los combos
		model.addAttribute("lstCategories",repoCat.findAll());
		model.addAttribute("lstTicketTypes",repoTicketType.findAll());
		return "editTicket";
	}
	
	//Grabar ticket vista editTicket
	@PostMapping("/saveTicket")
	public String saveTicket(@ModelAttribute Ticket ticket, Model model) {
		System.out.println(ticket);
		Ticket obj=new Ticket();
		obj.setTicket_id(ticket.getTicket_id());
		obj.setTicket_date_create(ticket.getTicket_date_create());
		obj.setTicket_description(ticket.getTicket_description());
		obj.setTicket_status(ticket.getTicket_status());
		obj.setTicket_title(ticket.getTicket_title());
		obj.setCategory_id(ticket.getCategory_id());
		obj.setIncident_user(ticket.getIncident_user());
		obj.setTicket_type_id(ticket.getTicket_type_id());
		obj.setAssigned_date(ticket.getAssigned_date());
		obj.setAssigned_user(ticket.getAssigned_user());
		obj.setFlag(true);
		repoTicket.save(obj);
		//return "redirect:/ticket";
		return "ticket";
	}
	
	
	
	//ELIMINAR
	@GetMapping("/ticket/deleteTicket/{ticket_id}")
//	@PreAuthorize("isAuthenticated()")
	@Transactional
	public String deleteTicket(@PathVariable Integer ticket_id) {
	    repoTicket.updateTicketFlag(ticket_id);
	    return "redirect:/ticket";
	}


}
