package com.GreenEnergy.soporteDeProblemas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "registro_problemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroProblema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioCliente;
    private String telefono;
    private String detalleProblema;

    private String estado = "pendiente";
    private LocalDate fechaSolicitud = LocalDate.now();
    private LocalDate fechaResolucion;
    private String solucion;
}
