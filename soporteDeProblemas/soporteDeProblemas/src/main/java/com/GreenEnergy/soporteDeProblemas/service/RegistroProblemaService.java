package com.GreenEnergy.soporteDeProblemas.service;

import com.GreenEnergy.soporteDeProblemas.dto.ProblemaRequestDTO;
import com.GreenEnergy.soporteDeProblemas.model.RegistroProblema;
import com.GreenEnergy.soporteDeProblemas.repository.RegistroProblemaRepository;
import com.GreenEnergy.soporteDeProblemas.webclient.ClienteClient;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RegistroProblemaService {

    @Autowired
    private RegistroProblemaRepository repo;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistroProblema crearProblema(ProblemaRequestDTO dto) {
        Map<String, Object> cliente = clienteClient.buscarClientePorUsuario(dto.getUsuarioCliente());

        if (cliente == null || !passwordEncoder.matches(dto.getPassword(), (String) cliente.get("password"))) {
            throw new RuntimeException("Usuario o contraseña inválidos");
        }

        RegistroProblema problema = new RegistroProblema();
        problema.setUsuarioCliente(dto.getUsuarioCliente());
        problema.setTelefono(dto.getTelefono());
        problema.setDetalleProblema(dto.getDetalleProblema());

        return repo.save(problema);
    }

    public List<RegistroProblema> listarTodos() {
        return repo.findAll();
    }

    public List<RegistroProblema> buscarPorEstado(String estado) {
        return repo.findByEstado(estado);
    }

    public List<RegistroProblema> buscarPorUsuario(String usuario) {
        return repo.findByUsuarioCliente(usuario);
    }

    public RegistroProblema cambiarEstado(Long id, String nuevoEstado) {
        RegistroProblema problema = repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        problema.setEstado(nuevoEstado);
        if ("solucionado".equalsIgnoreCase(nuevoEstado)) {
            problema.setFechaResolucion(LocalDate.now());
        }
        return repo.save(problema);
    }
}
