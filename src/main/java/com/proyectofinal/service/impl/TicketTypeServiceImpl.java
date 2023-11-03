package com.proyectofinal.service.impl;

import com.proyectofinal.model.TicketType;
import com.proyectofinal.repository.ITicketTypeRepo;
import com.proyectofinal.service.ITicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketTypeServiceImpl implements ITicketTypeService {

    @Autowired
    private ITicketTypeRepo ticketTypeRepo;

    @Override
    public TicketType register(TicketType entity) {
        return ticketTypeRepo.save(entity);
    }

    @Override
    public TicketType update(TicketType entity) {
        return ticketTypeRepo.save(entity);
    }

    @Override
    public List<TicketType> getAll() {
        return ticketTypeRepo.findAll();
    }

    @Override
    public TicketType getOne(int id) {
        Optional<TicketType> ticketType = ticketTypeRepo.findById(id);
        return ticketType.orElseGet(TicketType::new);
    }

    @Override
    public void delete(int id) {
        ticketTypeRepo.deleteById(id);
    }
}
