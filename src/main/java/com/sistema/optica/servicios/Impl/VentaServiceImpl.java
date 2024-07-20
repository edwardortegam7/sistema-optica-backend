package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Venta;
import com.sistema.optica.repositorios.VentaRepository;
import com.sistema.optica.servicios.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta agregarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }
}