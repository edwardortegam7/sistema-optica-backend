package com.sistema.optica.repositorios;

import com.sistema.optica.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
