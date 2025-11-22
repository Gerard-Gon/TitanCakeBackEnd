package com.titancake.TitanCake.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Carrito;
import com.titancake.TitanCake.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")//No estoy seguro la funcionalidad de esta etiqueta
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ItemCarritoService itemCarritoService;

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
    //Eliminacion normal y de cascada:Revisar en caso de no funcionar
    public void deleteById(Integer id) {
        itemCarritoService.deleteByCarritoId(id);
        carritoRepository.deleteById(id);
    }

    public void deleteByUsuarioId(Integer usuarioId) {
        List<Carrito> carritos = carritoRepository.findByUsuarioId(usuarioId);
        for (Carrito carrito : carritos) {
            this.deleteById(carrito.getId()); 
        }
    }

    public Carrito partialUpdate(Carrito carrito){
        Carrito existingCarrito = carritoRepository.findById(carrito.getId()).orElse(null);
        if (existingCarrito != null) {
            if (carrito.getUsuario() != null) {
                existingCarrito.setUsuario(carrito.getUsuario());
            }
            return carritoRepository.save(existingCarrito);
        }
        return null;
    }
    
}
