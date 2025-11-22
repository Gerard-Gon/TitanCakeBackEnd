package com.titancake.TitanCake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import com.titancake.TitanCake.model.ItemCarrito;
import com.titancake.TitanCake.service.ItemCarritoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/itemscarrito")
public class ItemCarritoController {

    @Autowired
    private ItemCarritoService itemCarritoService;


    @GetMapping
    @Operation(summary = "Muestra una lista de todos los ítems del carrito (general)")
    public ResponseEntity<List<ItemCarrito>> getAllItemCarritos() {
        List<ItemCarrito> items = itemCarritoService.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(items); 
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un ítem del carrito por su ID")
    public ResponseEntity<ItemCarrito> getItemCarritoById(@PathVariable Integer id) {
        ItemCarrito item = itemCarritoService.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(item);
    }


    @PostMapping
    @Operation(summary = "Agrega un nuevo ítem al carrito (o actualiza si existe)")
    public ResponseEntity<ItemCarrito> createItemCarrito(@RequestBody ItemCarrito itemCarrito) {
        itemCarrito.setId(null);
        ItemCarrito createdItem = itemCarritoService.save(itemCarrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un ítem del carrito completamente")
    public ResponseEntity<ItemCarrito> updateItemCarrito(@PathVariable Integer id, @RequestBody ItemCarrito itemCarrito) {
        itemCarrito.setId(id);
        ItemCarrito updatedItem = itemCarritoService.save(itemCarrito);
        
        if (updatedItem == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(updatedItem); 
    }


    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza un ítem del carrito parcialmente (ej. la cantidad)")
    public ResponseEntity<ItemCarrito> updatePartialItemCarrito(@PathVariable Integer id, @RequestBody ItemCarrito itemCarrito) {
        itemCarrito.setId(id);
        ItemCarrito updatedItem = itemCarritoService.partialUpdate(itemCarrito);

        if (updatedItem == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(updatedItem); 
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un ítem específico del carrito")
    public ResponseEntity<Void> deleteItemCarrito(@PathVariable Integer id) {
        itemCarritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
