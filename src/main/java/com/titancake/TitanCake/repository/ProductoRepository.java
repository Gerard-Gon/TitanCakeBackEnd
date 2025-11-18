package com.titancake.TitanCake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
