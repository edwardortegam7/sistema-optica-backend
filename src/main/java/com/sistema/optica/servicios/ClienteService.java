package com.sistema.optica.servicios;

import com.sistema.optica.entidades.Cliente;

public interface ClienteService {
    public Cliente guardarCliente(Cliente cliente) throws Exception;
    public Cliente obtenerCliente(String dni);
}
