package com.sistema.optica.excepciones;

public class EmpleadoNotFoundException extends Exception {
    public EmpleadoNotFoundException() {
        System.out.println("El empleado no existe, vuelva a intentar");
    }

    public EmpleadoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
