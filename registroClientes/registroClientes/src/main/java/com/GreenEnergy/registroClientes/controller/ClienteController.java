package com.GreenEnergy.registroClientes.controller;

import com.GreenEnergy.registroClientes.model.Cliente;
import com.GreenEnergy.registroClientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> lista = service.listarClientes();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarContacto(@PathVariable Long id, @RequestBody Map<String, String> datos) {
        String telefono = datos.get("telefono");
        String correo = datos.get("correo");
        return ResponseEntity.ok(service.actualizarContacto(id, telefono, correo));
    }
}
