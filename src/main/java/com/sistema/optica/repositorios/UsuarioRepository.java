package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByUsername(String username);

    @Query("SELECT u FROM Usuario u JOIN u.usuarioRoles ur JOIN ur.rol r WHERE r.nombre NOT IN ('ADMINISTRADOR', 'CLIENTE')")
    Set<Usuario> findAllExceptAdminAndClient();
}
