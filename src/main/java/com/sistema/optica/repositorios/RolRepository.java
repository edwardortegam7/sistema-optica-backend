package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
    public Rol findByNombre(String nombre);
}
