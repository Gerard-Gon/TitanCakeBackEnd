package com.titancake.TitanCake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito , Integer> {


    List<ItemCarrito> findByCarritoId(Integer carritoId); 
    List<ItemCarrito> findByProductoId(Integer productoId);
    
}
