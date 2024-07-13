package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UsuarioRepository extends JpaRepository<Employee,Long> {
    public Employee findByUsername(String username);

    @Query("SELECT cl.nombres, cl.apellidos, cl.telefono, cl.username, ci.fecha, ci.hora, ci.servicio " +
            "FROM Cliente cl " +
            "JOIN Cita ci " +
            "ON ci.cliente.id = cl.id")
    Set<Object[]> findAllClientAndDate();

    @Query("SELECT u FROM Employee u JOIN Rol r ON u.roles.id = r.id AND r.nombre NOT IN ('ADMINISTRADOR')")
    Set<Employee> findAllExceptAdmin();
}
