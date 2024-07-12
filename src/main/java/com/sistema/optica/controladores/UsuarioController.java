package com.sistema.optica.controladores;

import com.sistema.optica.entidades.Rol;
import com.sistema.optica.entidades.Usuario;
import com.sistema.optica.entidades.UsuarioRol;
import com.sistema.optica.servicios.RolService;
import com.sistema.optica.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");

        // Capitalizar nombres y apellidos
        usuario.setNombres(capitalize(usuario.getNombres()));
        usuario.setApellidos(capitalize(usuario.getApellidos()));

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        // Buscar o crear el rol "cliente"
        Rol rol = rolService.obtenerRol("CLIENTE");
        if (rol == null) {
            rol = new Rol();
            rol.setNombre("CLIENTE");
            rol = rolService.guardarRol(rol);
        }

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @GetMapping("/get-employees")
    public Set<Map<String, Object>> obtenerUsuariosExceptoAdminYCliente() {
        Set<Usuario> usuarios = usuarioService.obtenerUsuariosExceptoAdminYCliente();
        Set<Map<String, Object>> empleados = new HashSet<>();
        for (Usuario usuario : usuarios) {
            Map<String, Object> empleado = new HashMap<>();
            empleado.put("dni", usuario.getDni());
            empleado.put("name", usuario.getNombres());
            empleado.put("lastname", usuario.getApellidos());
            empleado.put("phone", usuario.getTelefono());
            empleado.put("rol", usuario.getAuthorities().stream().findFirst().get().getAuthority());
            empleados.add(empleado);
        }
        return empleados;
    }

    @GetMapping("/lista-solicitudes")
    public Set<Object[]> obtenerSolicitudes() {
        return usuarioService.obtenerSolicitudesCitas();
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
}
