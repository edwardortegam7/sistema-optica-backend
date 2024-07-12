package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Usuario;
import com.sistema.optica.entidades.UsuarioRol;

import java.util.Set;

public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    public Usuario obtenerUsuario(String username);
    public void eliminarUsuario(Long usuarioId);
    public Set<Usuario> obtenerUsuariosExceptoAdminYCliente();
    public Set<Object[]> obtenerSolicitudesCitas();
}
