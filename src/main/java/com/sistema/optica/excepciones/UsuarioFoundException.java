package com.sistema.optica.excepciones;

public class UsuarioFoundException extends Exception {

    public UsuarioFoundException() {
        System.out.println("El usuario con ese usuario ya existe, vuelva a intentar");
    }

    public UsuarioFoundException(String mensaje) {
        super(mensaje);
    }
}
