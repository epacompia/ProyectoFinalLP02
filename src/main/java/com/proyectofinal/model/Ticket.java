package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Column(name = "ticket_title", nullable = false, length = 30)
	private String ticket_title;
	
	@Column(name = "ticket_description", nullable = false, length = 500)
	private String ticket_description;
	
	@Column(name = "status", nullable = false, length = 30)
	private String status;
	
	@Column(name = "ticket_date_create")
	private LocalDateTime ticket_date_create;
	
	@ManyToOne
	@JoinColumn(name = "assigned_user", foreignKey = @ForeignKey(name = "fk_tickets_user_assigned"))
	private User assigned_user;
	
	@Column(name = "assigned_date")
	private LocalDateTime assigned_date;
	
	@ManyToOne
	@JoinColumn(name = "incident_user", nullable = false, foreignKey = @ForeignKey(name = "fk_tickets_user_incident"))
	private User incident_user;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ticket_category"))
	private Category category_id;
	
	private boolean flag;
	
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public Category getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}
	
	
	
	
	
	
}
