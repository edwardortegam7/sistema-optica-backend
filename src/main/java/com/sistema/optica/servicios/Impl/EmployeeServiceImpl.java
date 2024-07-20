package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Employee;
import com.sistema.optica.entidades.Rol;
import com.sistema.optica.excepciones.EmpleadoNotFoundException;
import com.sistema.optica.repositorios.RolRepository;
import com.sistema.optica.repositorios.EmpleadoRepository;
import com.sistema.optica.servicios.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Employee guardarEmpleado(Employee employee, String nombreRol) throws Exception {
        Employee employeeLocal = empleadoRepository.findByUsername(employee.getUsername());
        if (employeeLocal != null) {
            throw new Exception("El usuario ya está presente");
        } else {
            employee.setPerfil("default.png");
            employee.setNombres(capitalize(employee.getNombres()));
            employee.setApellidos(capitalize(employee.getApellidos()));

            employee.setPassword(this.bCryptPasswordEncoder.encode(employee.getPassword()));

            Rol rol = rolRepository.findByNombre(nombreRol);
            if (rol == null) {
                rol = new Rol();
                rol.setNombre(nombreRol);
                rolRepository.save(rol); // Guardar el nuevo rol
            }

            employee.setRoles(rol); // Asignar el rol al usuario
            employeeLocal = empleadoRepository.save(employee); // Guardar el usuario
        }

        return employeeLocal;
    }

    @Override
    public Employee obtenerEmpleado(String username) {
        return empleadoRepository.findByUsername(username);
    }

    @Override
    public Employee obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElseThrow( () -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public void eliminarEmpleado(Long id) throws EmpleadoNotFoundException {

        //empleadoRepository.deleteById(usuarioId);
        Employee employee = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException());
        empleadoRepository.delete(employee);
    }

    @Override
    public Set<Employee> obtenerEmpleadosExceptoAdmin() {
        return empleadoRepository.findAllExceptAdmin();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) throws EmpleadoNotFoundException {
        System.out.println(" back Employee details received: " + employeeDetails);
        System.out.println(employeeDetails.getApellidos());
        System.out.println(employeeDetails.getDni());
        System.out.println(employeeDetails.getNombres());
        System.out.println(employeeDetails.getId());
        Employee employee = empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException());
        employee.setNombres(employeeDetails.getNombres());
        employee.setApellidos(employeeDetails.getApellidos());
        employee.setDireccion(employeeDetails.getDireccion());
        employee.setTelefono(employeeDetails.getTelefono());
        employee.setDni(employeeDetails.getDni());
        employee.setGenero(employeeDetails.getGenero());
        // Agrega aquí cualquier otro campo que necesites actualizar
        return empleadoRepository.save(employee);
    }
    @Override
    public Set<Employee> obenerEmpleadosDoctor() {
        return empleadoRepository.findAllDoctor();
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