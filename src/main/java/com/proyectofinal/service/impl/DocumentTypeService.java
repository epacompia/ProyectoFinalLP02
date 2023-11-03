package com.proyectofinal.service.impl;

import com.proyectofinal.model.DocumentType;
import com.proyectofinal.repository.IDocumentTypeRepo;
import com.proyectofinal.service.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService implements IDocumentTypeService {

    @Autowired
    private IDocumentTypeRepo documentTypeRepo;

    @Override
    public DocumentType register(DocumentType entity) {
        return documentTypeRepo.save(entity);
    }

    @Override
    public DocumentType update(DocumentType entity) {
        return documentTypeRepo.save(entity);
    }

    @Override
    public List<DocumentType> getAll() {
        return documentTypeRepo.findAll();
    }

    @Override
    public DocumentType getOne(int id) {
        Optional<DocumentType> op = documentTypeRepo.findById(id);
        return op.orElseGet(DocumentType :: new);
    }

    @Override
    public void delete(int id) {
        documentTypeRepo.deleteById(id);
    }
}
