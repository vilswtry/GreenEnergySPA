package com.GreenEnergy.soporteDeProblemas.dto;

import lombok.Data;

@Data
public class ProblemaRequestDTO {
    private String usuarioCliente;
    private String password;
    private String telefono;
    private String detalleProblema;
}
