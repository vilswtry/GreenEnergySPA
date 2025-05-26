package com.GreenEnergy.coordinacionRecursos.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.GreenEnergy.coordinacionRecursos.DTO.MantencionRequest;
import com.GreenEnergy.coordinacionRecursos.DTO.ProjectRequest;
import com.GreenEnergy.coordinacionRecursos.model.Material;
import com.GreenEnergy.coordinacionRecursos.model.Tecnico;
import com.GreenEnergy.coordinacionRecursos.repository.MaterialRepository;
import com.GreenEnergy.coordinacionRecursos.repository.TecnicoRepository;

@Transactional
@Service
public class CoordinacionRecursosService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public void asignarRecursosProyecto(ProjectRequest request) {
        int cantPaneles = request.getCantidadPaneles();
        LocalDate fecha = request.getFechaProyecto();

        Map<String, Integer> materialesRequeridos = calcularMateriales(cantPaneles);

        for (Map.Entry<String, Integer> materialReq : materialesRequeridos.entrySet()) {
            Material material = materialRepository.findByCodigoMaterial(materialReq.getKey())
                    .orElseThrow(() -> new RuntimeException("Material no encontrado: " + materialReq.getKey()));
            if (material.getStock() < materialReq.getValue()) {
                throw new RuntimeException("Stock insuficiente de: " + material.getNombreMaterial());
            }
            material.setStock(material.getStock() - materialReq.getValue());
            materialRepository.save(material);
        }

        asignarTecnicos(fecha);
    }

    private Map<String, Integer> calcularMateriales(int cantPaneles) {
        Map<String, Integer> materiales = new HashMap<>();
        materiales.put("PS", cantPaneles);
        materiales.put("INV", cantPaneles / 5);
        materiales.put("CBDC", cantPaneles * 2);
        materiales.put("RIA", cantPaneles);
        materiales.put("ABR", cantPaneles * 2);
        materiales.put("GCH", cantPaneles);
        materiales.put("TRN", cantPaneles * 4);
        materiales.put("PTS", cantPaneles * 2);
        materiales.put("KPE", 1);
        materiales.put("CBAC", cantPaneles * 2);
        materiales.put("TAC", cantPaneles / 5);
        materiales.put("CBT", cantPaneles);
        materiales.put("VCT", 1);
        materiales.put("CAE", cantPaneles / 5);
        materiales.put("MC4", cantPaneles * 2);
        materiales.put("CNT", cantPaneles);
        materiales.put("ETQ", 1);
        materiales.put("EPP", 2);
        materiales.put("MULT", 1);
        materiales.put("CEP", 1);
        materiales.put("PAN", 1);
        materiales.put("AGP", 1);
        materiales.put("TST", 1);
        return materiales;
    }

    private void asignarTecnicos(LocalDate fecha) {
        List<String> especialidades = List.of("electricista", "instalador fotovoltaico", "instalador de estructura",
                "ayudante tecnico");

        for (String especialidad : especialidades) {
            List<Tecnico> disponibles = tecnicoRepository.findByEspecialidadAndFechaDisponible(especialidad, fecha);
            if (disponibles.isEmpty()) {
                throw new RuntimeException("No hay suficientes técnicos disponibles.");
            }
            Tecnico seleccionado = disponibles.get(0);
            seleccionado.getFechasOcupadas().add(fecha);
            tecnicoRepository.save(seleccionado);
        }
    }

    public void asignarRecursosMantencion(MantencionRequest request) {
        LocalDate fecha = request.getFechaMantencion();
        int paneles = request.getCantPanelesPorLimpiar();

        if (request.getFechaMantencion() == null || request.getCantPanelesPorLimpiar() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de mantención o la cantidad de paneles por limpiar no es válida.");
        }

        List<Tecnico> electricistasDisponibles = tecnicoRepository.findByEspecialidadAndFechaDisponible("electricista",
                fecha);
        if (electricistasDisponibles.isEmpty()) {
            throw new RuntimeException("No hay electricistas disponibles para la fecha indicada.");
        }
        Tecnico electricista = electricistasDisponibles.get(0);
        electricista.getFechasOcupadas().add(fecha);
        tecnicoRepository.save(electricista);

        int tecnicosLimpiezaNecesarios = (int) Math.ceil(paneles / 10.0);

        List<Tecnico> limpiezaDisponibles = tecnicoRepository
                .findByEspecialidadAndFechaDisponible("limpieza de paneles", fecha);
        if (limpiezaDisponibles.size() < tecnicosLimpiezaNecesarios) {
            throw new RuntimeException("No hay suficientes técnicos de limpieza disponibles para la fecha indicada.");
        }
        for (int i = 0; i < tecnicosLimpiezaNecesarios; i++) {
            Tecnico t = limpiezaDisponibles.get(i);
            t.getFechasOcupadas().add(fecha);
            tecnicoRepository.save(t);
        }

    }

    public List<Material> listarMateriales() {
        return materialRepository.findAll();
    }

    public List<Material> listarMaterialesFaltantes() {
        List<Material> materiales = materialRepository.findAll();

        return materiales.stream().filter(material -> {
            String codigo = material.getCodigoMaterial();
            int stock = material.getStock();

            return switch (codigo) {
                case "PS" -> stock < 31;
                case "INV" -> stock < 4;
                case "CBDC" -> stock < 121;
                case "RIA" -> stock < 22;
                case "ABR" -> stock < 136;
                case "GCH" -> stock < 91;
                case "TRN" -> stock < 136;
                case "PTS" -> stock < 136;
                case "KPE" -> stock < 4;
                case "CBAC" -> stock < 91;
                case "TAC" -> stock < 4;
                case "CBT" -> stock < 31;
                case "VCT" -> stock < 4;
                case "CAE" -> stock < 6;
                case "MC4" -> stock < 91;
                case "CNT" -> stock < 91;
                case "ETQ" -> stock < 4;
                case "EPP" -> stock < 4;
                case "MULT" -> stock < 4;
                case "CEP" -> stock < 10;
                case "PAN" -> stock < 10;
                case "AGP" -> stock < 61;
                case "TST" -> stock < 4;

                default -> false;
            };
        }).toList();
    }

    public Material reponerMaterial(String codigoMaterial, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reponer debe ser mayor a cero.");
        }

        Material material;
        try {
            material = buscarMaterialPorCodigo(codigoMaterial);
        } catch (RuntimeException e) {
            throw new RuntimeException("Código de material inválido: " + codigoMaterial);
        }

        int nuevoStock = material.getStock() + cantidad;
        material.setStock(nuevoStock);

        return materialRepository.save(material);
    }

    public Material buscarMaterialPorCodigo(String codigoMaterial) {
        return materialRepository.findByCodigoMaterial(codigoMaterial)
                .orElseThrow(
                        () -> new RuntimeException("Código de material inválido: " + codigoMaterial));
    }

}
