package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket_details")
public class TicketDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_detail_id")
	private int ticketDetail_id;
	
	@Column(name = "ticket_detail_description", length = 500)
	private String ticketDetailDescription;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "ticket_id")
	private Ticket ticket_id;
	
	@Column(name = "ticket_detail_date_create")
	private LocalDateTime ticketDetailDateCreate;
	
	private boolean flag;
	
	
	public int getTicketDetail_id() {
		return ticketDetail_id;
	}
	public void setTicketDetail_id(int ticketDetail_id) {
		this.ticketDetail_id = ticketDetail_id;
	}
	public String getTicketDetailDescription() {
		return ticketDetailDescription;
	}
	public void setTicketDetailDescription(String ticketDetailDescription) {
		this.ticketDetailDescription = ticketDetailDescription;
	}
	public LocalDateTime getTicketDetailDateCreate() {
		return ticketDetailDateCreate;
	}
	public void setTicketDetailDateCreate(LocalDateTime ticketDetailDateCreate) {
		this.ticketDetailDateCreate = ticketDetailDateCreate;
	}
	public Ticket getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(Ticket ticket_id) {
		this.ticket_id = ticket_id;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	
}
