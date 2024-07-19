package com.sistema.optica.servicios;


import com.sistema.optica.entidades.Inventario;

import java.util.List;


public interface InventarioService {
    List<Inventario> obtenerInventario();

    Inventario encontrarProductoPorId(Integer id);

    Inventario agregarProducto(Inventario inventario);

    Inventario modificarProducto(Integer id, Inventario detallesInvnetario);

    void eliminarProducto(Integer id);
}
