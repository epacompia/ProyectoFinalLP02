package com.proyectofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectofinal.config.UserService;
import com.proyectofinal.model.Ticket;
import com.proyectofinal.model.TicketStatus;
import com.proyectofinal.repository.ICategoryRepo;
import com.proyectofinal.repository.ITicketRepository;
import com.proyectofinal.repository.ITicketTypeRepository;
import com.proyectofinal.repository.IUserRepo;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyectofinal.model.*;

@Controller
public class TicketControllerOLD1 {

	@Autowired
	private ITicketRepository repoTicket;
	@Autowired
	private ICategoryRepo repoCat;
	@Autowired
	private ITicketTypeRepository repoTicketType;
	@Autowired
	private UserService userService;
	@Autowired
	private IUserRepo repoUser;
	
	//listar
	@GetMapping("/ticketOLD1")
	public String index(Model model) {
		// Obtener el usuario autenticado
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("Authentication: " + authentication);
	    if (authentication != null) {
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        com.proyectofinal.model.User user = repoUser.findByEmail(userDetails.getUsername());

	        if (user != null) {
	            // Imprimir información de depuración
	            System.out.println("Usuario que se autentico al sistema: " + user.getEmail());
	            System.out.println("Usuario rol: " + user.getRol_id());
	            System.out.println("Usuario nombre: " + user.getFirstname());
	            System.out.println("Usuario nombre: " + user.getSurname1());

	            // Pasando a la vista
	            model.addAttribute("firstname", user.getFirstname());
	            model.addAttribute("surname1", user.getSurname1());


	            // Listado
	            model.addAttribute("lstTickets", repoTicket.findAll());

	            return "ticket";
	        }
	    }
	 // En caso no redireccione
	    return "redirect:/login"; // O redirigir a una página de error
	}
	
	//editar
	@GetMapping("/editTicketOLD1/{ticket_id}")
	@PreAuthorize("isAuthenticated()")
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
	
	
	//Grabar datos editados
	@PostMapping("/saveTicketOLD1")
	public String grabarTicket(@ModelAttribute Ticket ticket, Model model) {
		System.out.println(ticket);
		return "redirect:/ticket";
		
	}
	
	
	
	//ELIMINAR
	@GetMapping("/ticket/deleteTicketOLD1/{ticket_id}")
	@PreAuthorize("isAuthenticated()")
	@Transactional
	public String deleteTicket(@PathVariable Integer ticket_id) {
	    repoTicket.updateTicketFlag(ticket_id);
	    return "redirect:/ticket";
	}


}
