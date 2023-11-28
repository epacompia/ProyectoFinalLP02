package com.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyectofinal.model.Ticket;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

	@Modifying
    @Query("UPDATE Ticket t SET t.flag = false WHERE t.ticket_id = :ticketId")
    void updateTicketFlag(@Param("ticketId") Integer ticketId);


    @Query(value = "select * from tickets where assigned_user = :assigned", nativeQuery = true)
    List<Ticket> findTIcketByAssignedUser(@Param("assigned") Integer assigned);

    @Query(value = "select * from tickets where incident_user = :user", nativeQuery = true)
    List<Ticket> findTIcketByUser(@Param("user") Integer user);


	List<Ticket> findByFlagTrue();

}
