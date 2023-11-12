package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticket_id;
	
	@Column(name = "ticket_title", nullable = false, length = 80)
	private String ticket_title;
	
	@Column(name = "ticket_description", nullable = false, length = 500)
	private String ticket_description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ticket_status", nullable = false, length = 30)
	private TicketStatus ticket_status;
	
	@Column(name = "ticket_date_create")
	private LocalDateTime ticket_date_create;
		
	@ManyToOne
	@JoinColumn(name = "incident_user", nullable = false, foreignKey = @ForeignKey(name = "fk_tickets_user_incident"))
	private User incident_user;	
	
	//Aqui ira el usuario al que se le asignara el tiket de atencion (este usuario debe tener un rol de soporte)
	@ManyToOne
	@JoinColumn(name = "assigned_user", foreignKey = @ForeignKey(name = "fk_tickets_user_assigned"))
	private User assigned_user;
	//Es la fecha en la que se asigno el ticket al usuario de soporte
	@Column(name = "assigned_date", nullable = true)
	private LocalDateTime assigned_date;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ticket_category"))
	private Category category_id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_type_id", nullable = false, foreignKey = @ForeignKey(name="fk_ticket_ticket_type"))
	private ticket_type ticket_type_id;
	
	private boolean flag;

	public Ticket() {
	}

	public Ticket(int ticket_id, String ticket_title, String ticket_description, TicketStatus ticket_status,
			LocalDateTime ticket_date_create, User assigned_user, LocalDateTime assigned_date, User incident_user,
			Category category_id, ticket_type ticket_type_id, boolean flag) {
		this.ticket_id = ticket_id;
		this.ticket_title = ticket_title;
		this.ticket_description = ticket_description;
		this.ticket_status = ticket_status;
		this.ticket_date_create = ticket_date_create;
		this.assigned_user = assigned_user;
		this.assigned_date = assigned_date;
		this.incident_user = incident_user;
		this.category_id = category_id;
		this.ticket_type_id = ticket_type_id;
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Ticket [ticket_id=" + ticket_id + ", ticket_title=" + ticket_title + ", ticket_description="
				+ ticket_description + ", ticket_status=" + ticket_status + ", ticket_date_create=" + ticket_date_create
				+ ", assigned_user=" + assigned_user + ", assigned_date=" + assigned_date + ", incident_user="
				+ incident_user + ", category_id=" + category_id + ", ticket_type_id=" + ticket_type_id + ", flag="
				+ flag + "]";
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public String getTicket_title() {
		return ticket_title;
	}

	public void setTicket_title(String ticket_title) {
		this.ticket_title = ticket_title;
	}

	public String getTicket_description() {
		return ticket_description;
	}

	public void setTicket_description(String ticket_description) {
		this.ticket_description = ticket_description;
	}

	public TicketStatus getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(TicketStatus ticket_status) {
		this.ticket_status = ticket_status;
	}

	public LocalDateTime getTicket_date_create() {
		return ticket_date_create;
	}

	public void setTicket_date_create(LocalDateTime ticket_date_create) {
		this.ticket_date_create = ticket_date_create;
	}

	public User getAssigned_user() {
		return assigned_user;
	}

	public void setAssigned_user(User assigned_user) {
		this.assigned_user = assigned_user;
	}

	public LocalDateTime getAssigned_date() {
		return assigned_date;
	}

	public void setAssigned_date(LocalDateTime assigned_date) {
		this.assigned_date = assigned_date;
	}

	public User getIncident_user() {
		return incident_user;
	}

	public void setIncident_user(User incident_user) {
		this.incident_user = incident_user;
	}

	public Category getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}

	public ticket_type getTicket_type_id() {
		return ticket_type_id;
	}

	public void setTicket_type_id(ticket_type ticket_type_id) {
		this.ticket_type_id = ticket_type_id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
	
	
}
