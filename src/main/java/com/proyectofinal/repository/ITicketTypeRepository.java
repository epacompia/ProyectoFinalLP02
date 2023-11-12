package com.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectofinal.model.ticket_type;

public interface ITicketTypeRepository extends JpaRepository<ticket_type, Integer> {

}
