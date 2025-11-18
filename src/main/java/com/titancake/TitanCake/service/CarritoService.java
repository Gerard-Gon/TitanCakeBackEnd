package com.titancake.TitanCake.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Carrito;
import com.titancake.TitanCake.repository.CarritoRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> getAllCarritos() {
    return carritoRepository.findAll();
    }
    public Carrito getCarritoById(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    public Carrito saveCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public void deleteCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

public Carrito patchCarrito(Long id, Carrito parcialCarrito){
        Optional<Carrito> carritoOptional = carritoRepository.findById(id);
        if (carritoOptional.isPresent()) {

            Carrito carritoToUpdate = carritoOptional.get();

            if (parcialCarrito.getCantidad()!=null) {
                carritoToUpdate.setCantidad(parcialCarrito.getCantidad());
            }
            if (parcialCarrito.getTotal()!=null) {
                carritoToUpdate.setTotal(parcialCarrito.getTotal());
            }
            
            return carritoRepository.save(carritoToUpdate);
        } else {
            return null; // or throw an exception
        }

    }
    
}
