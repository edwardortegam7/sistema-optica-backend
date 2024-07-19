package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

}
