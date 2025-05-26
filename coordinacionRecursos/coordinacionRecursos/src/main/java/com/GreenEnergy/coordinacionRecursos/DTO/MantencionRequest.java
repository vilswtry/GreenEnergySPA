package com.GreenEnergy.coordinacionRecursos.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MantencionRequest {
    private LocalDate fechaMantencion;
    private int cantPanelesPorLimpiar;
}
