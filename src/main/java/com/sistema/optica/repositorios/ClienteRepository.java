package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByDni(String dni);
}
