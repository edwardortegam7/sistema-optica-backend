package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente guardarCliente(Cliente cliente) throws Exception;

    Cliente obtenerCliente(String username);

    Cliente obtenerClienteId(Long id);

    List<Cliente> obtenerClientes();
}
