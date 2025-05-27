package com.GreenEnergy.generaReportes.service;

import com.GreenEnergy.generaReportes.model.Reporte;
import com.GreenEnergy.generaReportes.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository repo;

    public Reporte crear(Reporte r) {
        return repo.save(r);
    }

    public List<Reporte> listarTodos() {
        return repo.findAll();
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
