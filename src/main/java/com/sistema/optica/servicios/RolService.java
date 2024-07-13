package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Rol;

public interface RolService {
    Rol obtenerRol(String nombre);

    Rol guardarRol(Rol rol);
}
