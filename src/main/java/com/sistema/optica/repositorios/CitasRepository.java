package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CitasRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT cl.nombres, cl.apellidos, cl.telefono, cl.username, ci.fecha, ci.hora, ci.servicio, cl.id, ci.id " +
            "FROM Cliente cl " +
            "JOIN Cita ci " +
            "ON ci.cliente.id = cl.id " +
            "JOIN Employee e " +
            "ON ci.employee.id = e.id " +
            "AND e.id = 1"
    )
    Set<Object[]> findAllClientAndDate();

    @Query("SELECT cl.nombres, cl.apellidos, ci.fecha, ci.hora, e.nombres, e.apellidos " +
            "FROM Cliente cl " +
            "JOIN Cita ci " +
            "ON ci.cliente.id = cl.id " +
            "JOIN Employee e " +
            "ON ci.employee.id = e.id " +
            "AND e.id NOT IN (1)"
    )
    Set<Object[]> findAllDateAssigned();
}
