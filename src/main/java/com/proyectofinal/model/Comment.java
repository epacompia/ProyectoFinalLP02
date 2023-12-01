package com.proyectofinal.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commnent_id;
	
	@Column(name = "comment_content")
	private String comment_content;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_ticket"))
	private Ticket ticket_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_user"))
	private User user_id;
	
	
	private LocalDateTime comment_date;

	
	public int getCommnent_id() {
		return commnent_id;
	}

	public void setCommnent_id(int commnent_id) {
		this.commnent_id = commnent_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public Ticket getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Ticket ticket_id) {
		this.ticket_id = ticket_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public LocalDateTime getComment_date() {
		return comment_date;
	}

	public void setComment_date(LocalDateTime comment_date) {
		this.comment_date = comment_date;
	}
	
	
	

}
