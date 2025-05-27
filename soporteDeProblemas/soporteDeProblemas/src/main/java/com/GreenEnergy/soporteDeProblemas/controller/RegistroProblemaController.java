package com.GreenEnergy.soporteDeProblemas.controller;

import com.GreenEnergy.soporteDeProblemas.dto.ProblemaRequestDTO;
import com.GreenEnergy.soporteDeProblemas.model.RegistroProblema;
import com.GreenEnergy.soporteDeProblemas.service.RegistroProblemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/problemas")
public class RegistroProblemaController {

    @Autowired
    private RegistroProblemaService service;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody ProblemaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearProblema(dto));
    }

    @GetMapping
    public ResponseEntity<List<RegistroProblema>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/estado")
    public ResponseEntity<List<RegistroProblema>> buscarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(service.buscarPorEstado(estado));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<RegistroProblema>> buscarPorUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(service.buscarPorUsuario(usuario));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(service.cambiarEstado(id, estado));
    }
}
