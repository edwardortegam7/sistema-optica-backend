package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Employee;
import com.sistema.optica.excepciones.EmpleadoNotFoundException;

import java.util.Set;

public interface EmployeeService {
    Employee guardarEmpleado(Employee employee, String nombreRol) throws Exception;

    Employee obtenerEmpleado(String username);

    Employee obtenerEmpleadoPorId(Long id);

    void eliminarEmpleado(Long id) throws EmpleadoNotFoundException;

    Set<Employee> obtenerEmpleadosExceptoAdmin();

    Set<Employee> obenerEmpleadosDoctor();

    Employee updateEmployee(Long id, Employee employeeDetails) throws EmpleadoNotFoundException;

}
