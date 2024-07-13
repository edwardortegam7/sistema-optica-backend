package com.sistema.optica.excepciones;

public class UsuarioNotFoundException extends Exception {
    public UsuarioNotFoundException() {
        System.out.println("El usuario no existe, vuelva a intentar");
    }

    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}
