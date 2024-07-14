package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Employee;

import java.util.Set;

public interface UsuarioService {
    Employee guardarUsuario(Employee employee, String nombreRol) throws Exception;

    Employee obtenerUsuario(String username);

    Employee obtenerEmpleadoPorId(Long id);

    void eliminarUsuario(Long usuarioId);

    Set<Employee> obtenerUsuariosExceptoAdmin();

    Set<Object[]> obtenerSolicitudesCitas();
}
