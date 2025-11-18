package com.titancake.TitanCake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
}
