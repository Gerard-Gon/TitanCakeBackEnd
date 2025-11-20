package com.titancake.TitanCake.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Carrito;
import com.titancake.TitanCake.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Carrito findById(Integer id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        return carrito;
    }

    public Carrito save(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void deleteById(Integer id) {
        carritoRepository.deleteById(id);
    }
    public void deleteByProductoId(Integer productoId) {
        List<Carrito> carritos = carritoRepository.findAll();
        for (Carrito carrito : carritos) {
            if (carrito.getProducto() != null && carrito.getProducto().getId().equals(productoId)) {
                carritoRepository.deleteById(carrito.getId());
            }
        }
    }

    public void deleteByUsuarioId(Integer usuarioId) {
        List<Carrito> carritos = carritoRepository.findAll();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null && carrito.getUsuario().getId().equals(usuarioId)) {
                carritoRepository.deleteById(carrito.getId());
            }
        }
    }

    public Carrito partialUpdate(Carrito carrito){
        Carrito existingCarrito = carritoRepository.findById(carrito.getId()).orElse(null);
        if (existingCarrito != null) {
            if (carrito.getCantidad() != null) {
                existingCarrito.setCantidad(carrito.getCantidad());
            }
            if (existingCarrito.getTotal()!=null) {
                existingCarrito.setTotal(carrito.getTotal());
            }
            //Aca deberia agregar otra cosa a customizar como productos o no ?



            return carritoRepository.save(existingCarrito);
        }
        return null;
    }
    
}
