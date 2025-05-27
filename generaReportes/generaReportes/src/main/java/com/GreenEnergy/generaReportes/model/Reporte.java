package com.GreenEnergy.generaReportes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String empresa = "GreenEnergy";

    private String tipoReporte;
    private String descripcion;
    private String comentarios;

    private LocalDate fechaGeneracion = LocalDate.now();

    private String nombreCreador;
}
