package com.GreenEnergy.generaResenas.controller;

import com.GreenEnergy.generaResenas.model.Resena;
import com.GreenEnergy.generaResenas.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService service;

    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(r));
    }

    @GetMapping
    public ResponseEntity<List<Resena>> verTodas() {
        List<Resena> lista = service.verTodas();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }
}
