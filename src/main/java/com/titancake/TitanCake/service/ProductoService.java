package com.titancake.TitanCake.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Producto;
import com.titancake.TitanCake.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducto() {
    return productoRepository.findAll();
    }
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

public Producto patchProducto(Long id, Producto parcialProducto){
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {

            Producto productoToUpdate = productoOptional.get();

            if (parcialProducto.getNombreProducto()!=null) {
                productoToUpdate.setNombreProducto(parcialProducto.getNombreProducto());
            }
            if (parcialProducto.getDescripcionProducto()!=null) {
                productoToUpdate.setDescripcionProducto(parcialProducto.getDescripcionProducto());
            }
            if (parcialProducto.getPrecio()!=null) {
                productoToUpdate.setPrecio(parcialProducto.getPrecio());
            }
            if (parcialProducto.getStock()!=null) {
                productoToUpdate.setStock(parcialProducto.getStock());
            }
            return productoRepository.save(productoToUpdate);
        } else {
            return null; // or throw an exception
        }

    }
    
}
