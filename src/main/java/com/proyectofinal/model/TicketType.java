package com.proyectofinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "ticket_type")
public class TicketType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_type_id")
	private int ticket_type_id;
	
	@Column(name = "name_ticket_type")
	private String name_ticket_type;
	
	private boolean flag;

	
	
	public TicketType() {
	}



	public TicketType(int ticket_type_id, String name_ticket_type, boolean flag) {
		this.ticket_type_id = ticket_type_id;
		this.name_ticket_type = name_ticket_type;
		this.flag = flag;
	}



	@Override
	public String toString() {
		return "ticket_type [ticket_type_id=" + ticket_type_id + ", name_ticket_type=" + name_ticket_type + ", flag="
				+ flag + "]";
	}



	public int getTicket_type_id() {
		return ticket_type_id;
	}



	public void setTicket_type_id(int ticket_type_id) {
		this.ticket_type_id = ticket_type_id;
	}



	public String getName_ticket_type() {
		return name_ticket_type;
	}



	public void setName_ticket_type(String name_ticket_type) {
		this.name_ticket_type = name_ticket_type;
	}



	public boolean isFlag() {
		return flag;
	}



	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
