package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CitasRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT cl.nombres, cl.apellidos, cl.telefono, cl.username, ci.fecha, ci.hora, ci.servicio " +
            "FROM Cliente cl " +
            "JOIN Cita ci " +
            "ON ci.cliente.id = cl.id")
    Set<Object[]> findAllClientAndDate();
}
