package com.proyectofinal.controller;

import com.proyectofinal.model.Category;
import com.proyectofinal.model.ticket_type;
import com.proyectofinal.service.ITicketTypeService;
import com.proyectofinal.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TicketTypeController {

	@Autowired
	private ITicketTypeService ticketTypeServiceService;
	
	
	@GetMapping("/ticketstype")
	public String ticketstype(Model model) {
		List<ticket_type> ticketsType = ticketTypeServiceService.getAll();
		model.addAttribute("ticketsType", ticketsType);
		return "ticket-type";
	}
	
	
	@GetMapping("/ticketstype/new")
	public String saveTicketTypeForm(Model model) {
		ticket_type ticketType = new ticket_type();
		model.addAttribute("ticketType", ticketType);
		return "ticket-type-form";
	}
	
	@PostMapping("/ticketstype/new")
	public String saveTicketType(@ModelAttribute("ticketType") ticket_type ticketType, Model model) {
		ticket_type newTicketType = new ticket_type();
		newTicketType.setTicket_type_id(ticketType.getTicket_type_id());
		newTicketType.setName_ticket_type(ticketType.getName_ticket_type());
		newTicketType.setFlag(true);
		ticketTypeServiceService.register(newTicketType);
		return "redirect:/ticketstype";
	}
	
	@GetMapping("/ticketstype/edit/{id}")
	public String updateTicketTypeForm(@PathVariable Integer id,Model model) {

		ticket_type ticketType = ticketTypeServiceService.getOne(id);
		if(ticketType == null) {
			return "redirect:/ticketstype";
		}
		
		model.addAttribute("ticketType", ticketType);
		
		return "ticket-type-form";
	}
	
	@PostMapping("/ticketstype/edit/{id}")
	public String updateTicketType(@PathVariable Integer id, @ModelAttribute("ticketType") ticket_type ticketType, Model model) {
		ticket_type newTicketType = ticketTypeServiceService.getOne(id);
		newTicketType.setName_ticket_type(ticketType.getName_ticket_type());
		newTicketType.setFlag(ticketType.isFlag());
		ticketTypeServiceService.update(newTicketType);
		return "redirect:/ticketstype";
	}

	@GetMapping("/ticketstype/delete/{id}")
	public String deleteCategory(@PathVariable Integer id){
		ticketTypeServiceService.delete(id);
		return "redirect:/ticketstype";
	}
	
}
