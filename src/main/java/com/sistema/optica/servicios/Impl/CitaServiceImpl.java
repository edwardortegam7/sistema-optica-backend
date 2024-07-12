package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Cita;
import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.repositorios.CitasRepository;
import com.sistema.optica.servicios.CitaService;
import com.sistema.optica.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private ClienteService clienteService;

    @Override
    @Transactional
    public Cita guardarCita(Cita cita, Long clienteid) throws Exception {
        Cliente cliente = clienteService.obtenerClienteId(clienteid);
        cita.setCliente(cliente);
        return citasRepository.save(cita);
    }

    @Override
    public Set<Cita> obtenerCitas(Long idCliente) {
        return citasRepository.findAllByCliente(idCliente);
    }
}
