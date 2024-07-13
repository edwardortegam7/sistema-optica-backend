package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByUsername(String username);

    boolean existsByUsername(String username);
}
