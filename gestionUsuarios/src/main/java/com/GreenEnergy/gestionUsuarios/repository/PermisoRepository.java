package com.GreenEnergy.gestionUsuarios.repository;

import com.GreenEnergy.gestionUsuarios.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Optional<Permiso> findByNombre(String nombre);
}