package com.sistema.optica.entidades;
import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fecha", nullable=false, length=50)
    private String fecha;

    @Column(name="cliente", nullable=false, length=50)
    private String cliente;

    @Column(name="producto", nullable=false, length=50)
    private String producto;

    @Column(name="monto", nullable=false, precision=10, scale=2)
    private BigDecimal monto;

    @Column(name="estado", nullable=false, length=50)
    private String estado;

    public Venta(){

    }

    public Venta(int id, String fecha, String cliente, String producto, BigDecimal monto, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.producto = producto;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}