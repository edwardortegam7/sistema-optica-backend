package com.sistema.optica.controladores;

import com.sistema.optica.entidades.Cita;
import com.sistema.optica.entidades.Cliente;
import com.sistema.optica.entidades.Employee;
import com.sistema.optica.entidades.Inventario;
import com.sistema.optica.entidades.Venta;
import com.sistema.optica.servicios.CitaService;
import com.sistema.optica.servicios.ClienteService;
import com.sistema.optica.servicios.EmployeeService;
import com.sistema.optica.servicios.InventarioService;
import com.sistema.optica.servicios.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")

public class EmpleadoController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CitaService citaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private VentaService ventaService;

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
        return citaService.obtenerSolicitudesCitas();
    }

    @GetMapping("/check-email")
    public boolean checkEmailAvailability(@RequestParam("username") String email) {
        Employee employee = employeeService.obtenerEmpleado(email);
        return employee == null;
    }

    @GetMapping("/solicitud/{idSolicitud}")
    public Cita obtenerCita(@PathVariable Long idSolicitud) {
        return citaService.obtenerCita(idSolicitud);
    }

    @GetMapping("/get/{idCliente}")
    public Cliente obtenerCliente(@PathVariable Long idCliente) {
        return clienteService.obtenerClienteId(idCliente);
    }

    @GetMapping("/doctores")
    public Set<Employee> obtenerDoctores() {
        return employeeService.obenerEmpleadosDoctor();
    }

    @PutMapping("/asignar-cita/{idCita}")
    public ResponseEntity<Cita> asignarCita(@RequestParam("idEmployee") Long idEmployee, @PathVariable Long idCita) {
        try {
            Cita asignarCita = citaService.asignarCitaDoctor(idEmployee, idCita);
            return ResponseEntity.ok(asignarCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/citas-asignadas")
    public Set<Map<String, Object>> obtenerCitasAsignadas() {
        Set<Object[]> citas = citaService.obtenerCitasAsignadas();
        Set<Map<String,Object>> citasAsignadas = new HashSet<>();
        for (Object[] cita: citas) {
            Map<String, Object> citaAsignada = new HashMap<>();
            citaAsignada.put("nombres", cita[0]);
            citaAsignada.put("apellidos", cita[1]);
            citaAsignada.put("fecha", cita[2]);
            citaAsignada.put("hora", cita[3]);
            citaAsignada.put("nomDoctor", cita[4]);
            citaAsignada.put("apeDoctor", cita[5]);
            citasAsignadas.add(citaAsignada);
        }

        return citasAsignadas;
    }

    //Inventario
    @GetMapping("/inventario")
    public List<Inventario> obtenerInventario(){
        return inventarioService.obtenerInventario();
    }

    @GetMapping("/inventario/{id}")
    public Inventario encontrarProductoPorId(@PathVariable Integer id){
        return inventarioService.encontrarProductoPorId(id);
    }

    @PostMapping("/inventario")
    public Inventario agregarProducto(@RequestBody Inventario inventario){
        return inventarioService.agregarProducto(inventario);
    }


    @PutMapping("/inventario/{id}")
    public Inventario modificarProducto(@PathVariable Integer id,@RequestBody Inventario detallesInventario){
        return inventarioService.modificarProducto(id,detallesInventario);
    }

    @DeleteMapping("/inventario/{id}")
    public void eliminarProducto(@PathVariable Integer id){
        inventarioService.eliminarProducto(id);
    }

    //Venta
    @GetMapping("/ventas")
    public List<Venta> obtenerVentas(){
        return ventaService.obtenerVentas();
    }
}
