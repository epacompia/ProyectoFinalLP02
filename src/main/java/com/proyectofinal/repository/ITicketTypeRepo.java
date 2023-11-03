package com.proyectofinal.repository;

import com.proyectofinal.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketTypeRepo extends JpaRepository<TicketType, Integer> {
}
