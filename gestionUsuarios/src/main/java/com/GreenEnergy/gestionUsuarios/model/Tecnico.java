package com.GreenEnergy.gestionUsuarios.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    private String especialidad;
    private boolean disponible = true;
}