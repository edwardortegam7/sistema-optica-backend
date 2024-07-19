package com.sistema.optica.servicios.Impl;

import com.sistema.optica.entidades.Inventario;
import com.sistema.optica.repositorios.InventarioRepository;
import com.sistema.optica.servicios.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> obtenerInventario() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario encontrarProductoPorId(Integer id) {
        return inventarioRepository.findById(id).get();
    }

    @Override
    public Inventario agregarProducto(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @Override
    public Inventario modificarProducto(Integer id, Inventario detallesInventario) {
        Inventario inventario = inventarioRepository.findById(id).get();
        inventario.setCategoria(detallesInventario.getCategoria());
        inventario.setDescripcion(detallesInventario.getDescripcion());
        inventario.setPrecioVenta(detallesInventario.getPrecioVenta());
        inventario.setPrecioCompra(detallesInventario.getPrecioCompra());
        inventario.setStock(detallesInventario.getStock());
        inventarioRepository.save(inventario);
        return inventario;
    }

    @Override
    public void eliminarProducto(Integer id) {
        inventarioRepository.deleteById(id);
    }
}