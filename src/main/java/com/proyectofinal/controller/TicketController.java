package com.proyectofinal.controller;

import com.proyectofinal.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
//import org.springframework.security.access.prepost.PreAuthorize;
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
import com.proyectofinal.model.UserCustom;
import com.proyectofinal.repository.ICategoryRepo;
import com.proyectofinal.repository.ITicketRepository;
import com.proyectofinal.repository.ITicketTypeRepository;

import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.sql.DataSource;

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

	private UserService userService;
	@Autowired
	private IUserService uService;






	//listar
	@GetMapping("/ticket")
	public String index(HttpServletRequest request, Model model) {
		//1. Pasando el usuario de sesion a mi vista para ticket
		// Obtener el usuario autenticado

//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    String username = authentication.getName();
//	    // Obtener el UserDetails y hacer casting
//	    UserDetails userDetails = userService.loadUserByUsername(username);
//	    User user = (UserCustom) userDetails;

		Object userLogged = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserCustom userLoggedData = (UserCustom) userLogged;
		com.proyectofinal.model.User userActive = userLoggedData.getUser();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//variable que almacenará el rol del usuario de sessión
		String role = String.valueOf(auth.getAuthorities().stream().findFirst().get());

		if(role.equals("Administrador")) {
			model.addAttribute("lstTickets",repoTicket.findAll());
			return "ticket";
		}
		else {
			if (role.equals("Soporte")) {
				model.addAttribute("lstTickets", repoTicket.findTIcketByAssignedUser(userActive.getUser_id()));
				return "ticket";
			}
			else{
				model.addAttribute("lstTickets", repoTicket.findTIcketByUser(userActive.getUser_id()));
				return "ticket";
			}
		}

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


	@GetMapping("/adm/newticket")
	public String createTicketAdm(Model model) {
		Ticket t=new Ticket();
		model.addAttribute("ticket",t);
		//Agregando datos del enum
		model.addAttribute("valuesForTicketStatusEnum",TicketStatus.values());
		//llamado a los combos
		model.addAttribute("lstCategories",repoCat.findAll());
		model.addAttribute("lstTicketTypes",repoTicketType.findAll());
		model.addAttribute("incidentUsers", uService.getAll());
		model.addAttribute("assignedUsers", uService.getSupportUser());
		return "newTicket-adm";
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


	@PostMapping("/adm/saveTicket")
	public String saveTicketAdm(@ModelAttribute Ticket ticket, Model model) {
		//System.out.println(ticket);
		Ticket obj=new Ticket();
		obj.setTicket_id(ticket.getTicket_id());
		obj.setTicket_date_create(LocalDateTime.now());
		obj.setTicket_description(ticket.getTicket_description());
		obj.setTicket_status(TicketStatus.SIN_ASIGNAR.toString());
		obj.setTicket_title(ticket.getTicket_title());
		obj.setCategory_id(ticket.getCategory_id());
		obj.setIncident_user(ticket.getIncident_user());
		obj.setTicket_type_id(ticket.getTicket_type_id());
		obj.setAssigned_date(LocalDateTime.now());
		obj.setAssigned_user(ticket.getAssigned_user());
		obj.setFlag(true);
		repoTicket.save(obj);
		return "redirect:/ticket";
	}


	//editar
	@GetMapping("/editTicket/{ticket_id}")
//	@PreAuthorize("isAuthenticated()")
	public String editTicket(@PathVariable Integer ticket_id, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//variable que almacenará el rol del usuario de sessión
		String role = String.valueOf(auth.getAuthorities().stream().findFirst().get());

		Ticket t=repoTicket.findById(ticket_id).get();

		if(role.equals("Administrador")){
			model.addAttribute("administrador", true);
			model.addAttribute("assignedUsers", uService.getSupportUser());
		}

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
		//System.out.println(ticket);


		Ticket oldTicket = repoTicket.getById(ticket.getTicket_id());

		oldTicket.setTicket_date_create(oldTicket.getTicket_date_create());
		oldTicket.setTicket_description(ticket.getTicket_description());

		oldTicket.setTicket_title(ticket.getTicket_title());
		oldTicket.setCategory_id(ticket.getCategory_id());
		oldTicket.setIncident_user(oldTicket.getIncident_user());
		oldTicket.setTicket_type_id(ticket.getTicket_type_id());
		oldTicket.setFlag(true);

		if(ticket.getAssigned_user() == oldTicket.getAssigned_user()){
			oldTicket.setTicket_status(TicketStatus.ASIGNADO.toString());
			oldTicket.setAssigned_date(oldTicket.getAssigned_date());
			oldTicket.setAssigned_user(oldTicket.getAssigned_user());
		}

		if(ticket.getAssigned_user() != oldTicket.getAssigned_user()){
			oldTicket.setTicket_status(TicketStatus.ASIGNADO.toString());
			oldTicket.setAssigned_date(LocalDateTime.now());
			oldTicket.setAssigned_user(ticket.getAssigned_user());
		}

//		Ticket obj=new Ticket();
//		obj.setTicket_id(ticket.getTicket_id());
//		obj.setTicket_date_create(ticket.getTicket_date_create());
//		obj.setTicket_description(ticket.getTicket_description());
//		obj.setTicket_status(ticket.getTicket_status());
//		obj.setTicket_title(ticket.getTicket_title());
//		obj.setCategory_id(ticket.getCategory_id());
//		obj.setIncident_user(ticket.getIncident_user());
//		obj.setTicket_type_id(ticket.getTicket_type_id());



//		obj.setFlag(true);
		repoTicket.save(oldTicket);
		return "redirect:/ticket";
		//return "ticket";
	}




	@GetMapping("/ticket/delete/{ticket_id}")
	@PreAuthorize("isAuthenticated()")
	@Transactional
	public String deleteTicket(@PathVariable Integer ticket_id) {
		Optional<Ticket> codEncontradoTicket=repoTicket.findById(ticket_id);
		
		if(codEncontradoTicket.isPresent()) {
			Ticket ticket=codEncontradoTicket.get();
			ticket.setFlag(false);
			repoTicket.save(ticket);
			return "redirect:/ticket";
		}else {
			return "redirect:/error";
		}
		
	}
	
	//REPORTE PDF
	@Autowired
	private DataSource dataSource; // javax.sql
	@Autowired
	private ResourceLoader resourceLoader; // core.io
	@GetMapping("/reportes")
	public void reportes(HttpServletResponse response) {
	//response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
	response.setHeader("Content-Disposition", "inline;");
	response.setContentType("application/pdf");
	try {
	String ru = resourceLoader.getResource("classpath:reporteTickets.jasper").getURI().getPath();
	JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
	OutputStream outStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	
	
	
	//REPORTE GRAFICO
		
		@GetMapping("/graficos")
		public void reportesGraficos(HttpServletResponse response) {
		//response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Content-Disposition", "inline;");
		response.setContentType("application/pdf");
		try {
		String ru = resourceLoader.getResource("classpath:graficoTickets.jasper").getURI().getPath();
		JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}



}
