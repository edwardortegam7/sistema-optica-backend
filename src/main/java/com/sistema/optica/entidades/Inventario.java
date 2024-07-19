package com.sistema.optica.entidades;
import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="categoria", nullable=false, length=50)
    private String categoria;

    @Column(name="descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name="precioCompra", nullable=false, precision=10, scale=2)
    private BigDecimal precioCompra;

    @Column(name="precioVenta", nullable=false, precision=10, scale=2)
    private BigDecimal precioVenta;

    @Column(name="utilidad", nullable = false, precision = 10, scale=2)
    private BigDecimal utilidad;

    @Column(name="stock", nullable = false)
    private int stock;

    public Inventario(){

    }

    public Inventario(int id, String categoria, String descripcion, BigDecimal precioCompra, BigDecimal precioVenta, BigDecimal utilidad, int stock) {
        this.id = id;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.utilidad = utilidad;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}