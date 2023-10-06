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
	private String ticket_detail_description;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "ticket_id")
	private Ticket ticket_id;
	
	@Column(name = "ticket_detail_date_create")
	private LocalDateTime ticket_detail_date_create;
	
	private boolean flag;
	
	
	public int getTicketDetail_id() {
		return ticketDetail_id;
	}
	public void setTicketDetail_id(int ticketDetail_id) {
		this.ticketDetail_id = ticketDetail_id;
	}
	
	public String getTicket_detail_description() {
		return ticket_detail_description;
	}
	public void setTicket_detail_description(String ticket_detail_description) {
		this.ticket_detail_description = ticket_detail_description;
	}
	public LocalDateTime getTicket_detail_date_create() {
		return ticket_detail_date_create;
	}
	public void setTicket_detail_date_create(LocalDateTime ticket_detail_date_create) {
		this.ticket_detail_date_create = ticket_detail_date_create;
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
