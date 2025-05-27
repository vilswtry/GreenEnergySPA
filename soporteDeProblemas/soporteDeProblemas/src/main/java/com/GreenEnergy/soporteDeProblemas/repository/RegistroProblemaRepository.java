package com.GreenEnergy.soporteDeProblemas.repository;

import com.GreenEnergy.soporteDeProblemas.model.RegistroProblema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroProblemaRepository extends JpaRepository<RegistroProblema, Long> {
    List<RegistroProblema> findByEstado(String estado);
    List<RegistroProblema> findByUsuarioCliente(String usuarioCliente);
}
