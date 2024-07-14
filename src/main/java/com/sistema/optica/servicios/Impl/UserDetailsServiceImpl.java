package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Employee;
import com.sistema.optica.repositorios.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.empleadoRepository.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return employee;
    }
}
