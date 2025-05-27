package com.GreenEnergy.generaResenas.service;

import com.GreenEnergy.generaResenas.model.Resena;
import com.GreenEnergy.generaResenas.repository.ResenaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository repo;

    public Resena guardar(Resena r) {
        return repo.save(r);
    }

    public List<Resena> verTodas() {
        return repo.findAll();
    }
}
