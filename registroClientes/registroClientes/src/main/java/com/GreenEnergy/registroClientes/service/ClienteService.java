package com.GreenEnergy.registroClientes.service;

import com.GreenEnergy.registroClientes.model.Cliente;
import com.GreenEnergy.registroClientes.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listarClientes() {
        return repo.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        repo.deleteById(id);
    }

    public Cliente actualizarContacto(Long id, String telefono, String correo) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        return repo.save(cliente);
    }
}
