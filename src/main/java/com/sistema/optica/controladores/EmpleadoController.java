package com.sistema.optica.controladores;

import com.sistema.optica.entidades.Employee;
import com.sistema.optica.servicios.RolService;
import com.sistema.optica.servicios.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")

public class EmpleadoController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolService rolService;

    @PostMapping("/{nombreRol}")
    public ResponseEntity<Employee> guardarEmpleado(@RequestBody Employee employee, @PathVariable String nombreRol) {
        try {
            Employee employeeGuardado = employeeService.guardarEmpleado(employee, nombreRol);
            return ResponseEntity.ok(employeeGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{username}")
    public Employee obtenerEmpleado(@PathVariable("username") String username) {
        return employeeService.obtenerEmpleado(username);
    }

    @DeleteMapping("/{empleadoId}")
    public void eliminarEmpleado(@PathVariable("empleadoId") Long empleadoId) {
        employeeService.eliminarEmpleado(empleadoId);
    }

    @GetMapping("/get-employees")
    public Set<Map<String, Object>> obtenerEmpleadoExceptoAdmin() {
        Set<Employee> employees = employeeService.obtenerEmpleadosExceptoAdmin();
        Set<Map<String, Object>> empleados = new HashSet<>();
        for (Employee employee : employees) {
            Map<String, Object> empleado = new HashMap<>();
            empleado.put("dni", employee.getDni());
            empleado.put("name", employee.getNombres());
            empleado.put("lastname", employee.getApellidos());
            empleado.put("phone", employee.getTelefono());
            empleado.put("rol", employee.getAuthorities().stream().findFirst().get().getAuthority());
            empleados.add(empleado);
        }
        return empleados;
    }

    @GetMapping("/lista-solicitudes")
    public Set<Object[]> obtenerSolicitudes() {
        return employeeService.obtenerSolicitudesCitas();
    }

    @GetMapping("/check-email")
    public boolean checkEmailAvailability(@RequestParam("username") String email) {
        Employee employee = employeeService.obtenerEmpleado(email);
        return employee == null;
    }
}
