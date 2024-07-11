package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Rol;
import com.sistema.optica.repositorios.RolRepository;
import com.sistema.optica.servicios.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol obtenerRol(String nombre) {
        return rolRepository.findByNombre(nombre);
    }

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
}
