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

import com.titancake.TitanCake.model.Carrito;
import com.titancake.TitanCake.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    @Operation(summary = "Muestra una lista de los carritos")
    public ResponseEntity<List<Carrito>> getAllCartrCarritos() {
        List<Carrito> carritos = carritoService.findAll();
        if (carritos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carritos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener carrito por su id")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Integer id) {
        Carrito carrito = carritoService.findById(id);
        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrito);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carrito> partialUpdateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        Carrito existingCarrito = carritoService.findById(id);
        if (existingCarrito == null) {
            return ResponseEntity.notFound().build();  
        }
        return ResponseEntity.ok(carritoService.partialUpdate(carrito));
    }
    @PostMapping
    @Operation(summary = "Agrega un carrito")
    public ResponseEntity<Carrito> createCarrito(@RequestBody Carrito carrito) {
        carrito.setId(null); 
        Carrito createdCarrito = carritoService.save(carrito);
        return ResponseEntity.status(201).body(createdCarrito);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un carrito")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        carrito.setId(id);
        Carrito updatedCarrito = carritoService.save(carrito);
        if (updatedCarrito == null) {
            return ResponseEntity.notFound().build();  
        }
        return ResponseEntity.ok(updatedCarrito);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un carrito")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Integer id) {
        carritoService.deleteById(id);
        return ResponseEntity.noContent().build();  
    }
    
}
