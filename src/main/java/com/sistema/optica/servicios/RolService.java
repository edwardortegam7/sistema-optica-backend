package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Rol;

public interface RolService {
    public Rol obtenerRol(String nombre);
    public Rol guardarRol(Rol rol);
}
