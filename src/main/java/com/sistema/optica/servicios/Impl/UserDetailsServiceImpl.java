package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Employee;
import com.sistema.optica.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.usuarioRepository.findByUsername(username);
        if(employee == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return employee;
    }
}
