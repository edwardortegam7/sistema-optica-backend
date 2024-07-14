package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Employee;

import java.util.Set;

public interface EmployeeService {
    Employee guardarEmpleado(Employee employee, String nombreRol) throws Exception;

    Employee obtenerEmpleado(String username);

    Employee obtenerEmpleadoPorId(Long id);

    void eliminarEmpleado(Long usuarioId);

    Set<Employee> obtenerEmpleadosExceptoAdmin();

    Set<Object[]> obtenerSolicitudesCitas();
}
