package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.repositorios.ClienteRepository;
import com.sistema.optica.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente guardarCliente(Cliente cliente) throws Exception {
        Cliente clienteLocal = clienteRepository.findByUsername(cliente.getUsername());

        if (clienteLocal != null) {
            throw new Exception("El cliente ya está registrado");
        } else {
            clienteLocal = clienteRepository.save(cliente);
        }
        return clienteLocal;
    }

    @Override
    public Cliente obtenerCliente(String username) {
        return clienteRepository.findByUsername(username);
    }

    @Override
    public Cliente obtenerClienteId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
