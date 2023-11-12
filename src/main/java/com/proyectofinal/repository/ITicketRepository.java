package com.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectofinal.model.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

	@Modifying
    @Query("UPDATE Ticket t SET t.flag = false WHERE t.ticket_id = :ticketId")
    void updateTicketFlag(@Param("ticketId") Integer ticketId);
}
