package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Cita;
import com.sistema.optica.entidades.Cliente;

import java.util.Set;

public interface CitaService {
    Cita guardarCita(Cita cita, Long clienteid) throws Exception;

    Set<Object[]> obtenerSolicitudesCitas();

    Cita obtenerCita(Long id);
}
