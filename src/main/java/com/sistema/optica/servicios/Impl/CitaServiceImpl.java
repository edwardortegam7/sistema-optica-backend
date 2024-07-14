package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Cita;
import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.entidades.Employee;
import com.sistema.optica.repositorios.CitasRepository;
import com.sistema.optica.servicios.CitaService;
import com.sistema.optica.servicios.ClienteService;
import com.sistema.optica.servicios.EmployeeService;
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

    @Autowired
    private EmployeeService employeeService;

    @Override
    @Transactional
    public Cita guardarCita(Cita cita, Long clienteid) throws Exception {
        Long employeeId = 1L;
        Cliente cliente = clienteService.obtenerClienteId(clienteid);
        Employee employee = employeeService.obtenerEmpleadoPorId(employeeId);
        cita.setCliente(cliente);
        cita.setEmployee(employee);
        return citasRepository.save(cita);
    }

    @Override
    public Set<Object[]> obtenerSolicitudesCitas() {
        return citasRepository.findAllClientAndDate();
    }

    @Override
    public Cita obtenerCita(Long id) {
        return citasRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }
}
