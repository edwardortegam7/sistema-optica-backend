package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = this.clienteRepository.findByUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente no encontrado");
        }
        return cliente;
    }

    public boolean esCliente(String username) {
        return clienteRepository.existsByUsername(username);
    }
}
