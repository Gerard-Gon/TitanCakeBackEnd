package com.titancake.TitanCake.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titancake.TitanCake.model.ItemCarrito;
import com.titancake.TitanCake.repository.ItemCarritoRepository; 

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ItemCarritoService {

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    public List<ItemCarrito> findAll() {
        return itemCarritoRepository.findAll();
    }

    public ItemCarrito findById(Integer id) {
        ItemCarrito itemCarrito = itemCarritoRepository.findById(id).orElse(null);
        return itemCarrito;
    }

    public ItemCarrito save(ItemCarrito itemCarrito) {
        return itemCarritoRepository.save(itemCarrito);
    }


    public void deleteById(Integer id) {
        itemCarritoRepository.deleteById(id);
    }


    public ItemCarrito partialUpdate(ItemCarrito itemCarrito){
        ItemCarrito existingItem = itemCarritoRepository.findById(itemCarrito.getId()).orElse(null);
        
        if (existingItem != null) {
            
            if (itemCarrito.getCantidad() != null) {
                existingItem.setCantidad(itemCarrito.getCantidad());
            }
            
            if (itemCarrito.getPrecioUnitario() != null) {
                existingItem.setPrecioUnitario(itemCarrito.getPrecioUnitario());
            }
            return itemCarritoRepository.save(existingItem);
        }
        return null;
    }
}