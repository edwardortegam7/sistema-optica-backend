package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Rol;
import com.sistema.optica.entidades.Usuario;
import com.sistema.optica.entidades.UsuarioRol;
import com.sistema.optica.repositorios.RolRepository;
import com.sistema.optica.repositorios.UsuarioRepository;
import com.sistema.optica.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioLocal != null) {
            throw new Exception("El usuario ya está presente");
        } else {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                Rol rol = usuarioRol.getRol();
                // Verifica si el rol ya existe en la base de datos
                Rol rolExistente = rolRepository.findByNombre(rol.getNombre());
                if (rolExistente == null) {
                    if (rol.getNombre() == null || rol.getNombre().isEmpty()) {
                        throw new Exception("El nombre del rol no puede ser nulo o vacío");
                    }
                    // Guarda el nuevo rol
                    rol = rolRepository.save(rol);
                } else {
                    rol = rolExistente;
                }
                usuarioRol.setRol(rol);
            }

            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }

        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Set<Usuario> obtenerUsuariosExceptoAdminYCliente() {
        return usuarioRepository.findAllExceptAdminAndClient();
    }

    @Override
    public Set<Object[]> obtenerSolicitudesCitas() {
        return usuarioRepository.findAllClientAndDate();
    }
}