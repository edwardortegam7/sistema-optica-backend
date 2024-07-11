package com.sistema.optica.controladores;

import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.repositorios.ClienteRepository;
import com.sistema.optica.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Cliente guardarCliente(@RequestBody Cliente cliente) throws Exception {
        cliente.setNombres(capitalize(cliente.getNombres()));
        cliente.setApellidos(capitalize(cliente.getApellidos()));
        cliente.setPassword(this.bCryptPasswordEncoder.encode(cliente.getPassword()));

        return clienteService.guardarCliente(cliente);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split(" ");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String word : words) {
            if (word.length() > 1) {
                capitalizedWords.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            } else {
                capitalizedWords.append(word.toUpperCase());
            }
            capitalizedWords.append(" ");
        }
        return capitalizedWords.toString().trim();
    }

    @GetMapping("/es-cliente/{username}")
    public boolean esCliente(@PathVariable String username) {
        Cliente cliente = clienteRepository.findByUsername(username);
        return cliente != null;
    }
}