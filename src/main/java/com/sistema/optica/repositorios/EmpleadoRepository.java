package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface EmpleadoRepository extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);

    @Query("SELECT u FROM Employee u JOIN Rol r ON u.roles.id = r.id AND r.nombre NOT IN ('ADMINISTRADOR')")
    Set<Employee> findAllExceptAdmin();
}
