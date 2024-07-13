package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Employee;

import java.util.Set;

public interface UsuarioService {
    public Employee guardarUsuario(Employee employee, String nombreRol) throws Exception;
    public Employee obtenerUsuario(String username);
    public void eliminarUsuario(Long usuarioId);
    public Set<Employee> obtenerUsuariosExceptoAdmin();
    public Set<Object[]> obtenerSolicitudesCitas();
}
