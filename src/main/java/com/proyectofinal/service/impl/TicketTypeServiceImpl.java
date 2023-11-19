package com.proyectofinal.service.impl;

import com.proyectofinal.model.ticket_type;
import com.proyectofinal.repository.ITicketTypeRepository;
import com.proyectofinal.service.ITicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeServiceImpl implements ITicketTypeService {

    @Autowired
    private ITicketTypeRepository ticketTypeRepo;

    @Override
    public ticket_type register(ticket_type entity) {
        return ticketTypeRepo.save(entity);
    }

    @Override
    public ticket_type update(ticket_type entity) {
        return ticketTypeRepo.save(entity);
    }

    @Override
    public List<ticket_type> getAll() {
        return ticketTypeRepo.findAll();
    }

    @Override
    public ticket_type getOne(int id) {
        Optional<ticket_type> ticketType = ticketTypeRepo.findById(id);
        return ticketType.orElseGet(ticket_type::new);
    }

    @Override
    public void delete(int id) {
        ticketTypeRepo.deleteById(id);
    }
}
