package com.titancake.TitanCake.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Producto;
import com.titancake.TitanCake.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;


    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Integer id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        return producto;
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto partialUpdate(Producto producto){
        Producto existingProducto = productoRepository.findById(producto.getId()).orElse(null);
        if (existingProducto != null) {
            if (producto.getNombreProducto()!= null) {
                existingProducto.setNombreProducto(producto.getNombreProducto());
            }
            if (producto.getDescripcionProducto()!= null){
                existingProducto.setDescripcionProducto(producto.getDescripcionProducto());
            }
            if (producto.getPrecio()!=null) {
                existingProducto.setPrecio(producto.getPrecio());
            }
            if(producto.getStock()!=null){
                existingProducto.setStock(producto.getStock());
            }
            if (producto.getImageUrl()!=null) {
                existingProducto.setImageUrl(producto.getImageUrl());
            }
            if (producto.getCategoria()!=null) {
                existingProducto.setCategoria(producto.getCategoria());
            }

            return productoRepository.save(existingProducto);
        }
        return null;
    }
    
}
