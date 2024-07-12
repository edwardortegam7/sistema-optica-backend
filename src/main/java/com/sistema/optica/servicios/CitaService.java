package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Cita;
import com.sistema.optica.entidades.Cliente;

import java.util.Set;

public interface CitaService {
    public Cita guardarCita(Cita cita, Long clienteid) throws Exception;
    public Set<Cita> obtenerCitas(Long idCliente);
}
