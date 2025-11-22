package com.titancake.TitanCake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito , Integer> {


    List<ItemCarritoRepository> findByCarritoId(Integer carritoId); 
    List<ItemCarritoRepository> findByProductoId(Integer productoId);
    
}
