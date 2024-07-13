package com.sistema.optica.controladores;

import com.sistema.optica.config.JwtUtils;
import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.entidades.JwtRequest;
import com.sistema.optica.entidades.JwtResponse;
import com.sistema.optica.entidades.Employee;
import com.sistema.optica.excepciones.UsuarioNotFoundException;
import com.sistema.optica.servicios.Impl.ClienteDetailsServiceImpl;
import com.sistema.optica.servicios.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClienteDetailsServiceImpl clienteDetailsService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        UserDetails userDetails;

        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UsuarioNotFoundException exception) {
            throw new Exception("Usuario no encontrado 1");
        }

        // Determinar si es un cliente o un usuario normal
        if (esCliente(jwtRequest.getUsername())) {
            userDetails = clienteDetailsService.loadUserByUsername(jwtRequest.getUsername());
        } else {
            userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        }

        // Generar token
        String token = this.jwtUtils.generateToken(userDetails);

        // Devolver la respuesta
        return ResponseEntity.ok(new JwtResponse(token));
        /*
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
         */
    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO " + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales invalidas " + badCredentialsException.getMessage());
        }
    }

    private boolean esCliente(String username) {
        return clienteDetailsService.esCliente(username);
    }

    /*
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    */
    @GetMapping("/actual-usuario")
    public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
        String username = principal.getName();
        UserDetails userDetails;

        // Intentar cargar detalles del usuario
        try {
            userDetails = this.userDetailsService.loadUserByUsername(username);
            return ResponseEntity.ok((Employee) userDetails);
        } catch (UsernameNotFoundException e) {
            // Si no se encuentra como usuario, intentar cargar detalles del cliente
            try {
                userDetails = this.clienteDetailsService.loadUserByUsername(username);
                return ResponseEntity.ok((Cliente) userDetails);
            } catch (UsernameNotFoundException ex) {
                // Si no se encuentra ni como usuario ni como cliente, devolver un error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o cliente no encontrado");
            }
        }
    }

}
