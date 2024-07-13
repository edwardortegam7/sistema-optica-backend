package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CitasRepository extends JpaRepository<Cita, Long> {
    Set<Cita> findAllByCliente(Long idCliente);
}
