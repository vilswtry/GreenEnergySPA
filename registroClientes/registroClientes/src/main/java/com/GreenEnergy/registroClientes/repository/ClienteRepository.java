package com.GreenEnergy.registroClientes.repository;

import com.GreenEnergy.registroClientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
