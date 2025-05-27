package com.GreenEnergy.generaResenas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;
    private String usuario;
    private String codigoServicio;
    private int calificacion;
    private String comentarios;
}
