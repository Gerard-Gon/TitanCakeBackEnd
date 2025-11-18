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

import com.titancake.TitanCake.model.Carrito;
import com.titancake.TitanCake.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    @Operation(summary = "Muestra una lista de los carritos")
    public List<Carrito> getAllCartrCarritos() {
        return carritoService.getAllCarritos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener carrito por su id")
    public Carrito getCarritoById(@PathVariable Long id) {
        return carritoService.getCarritoById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar un carrito parcialmente")
    public ResponseEntity<Carrito> patchProducto(@PathVariable Long id , @RequestBody Carrito parcialCarrito){
        try {
            Carrito updateCarrito = carritoService.patchCarrito(id, parcialCarrito);
            return ResponseEntity.ok(updateCarrito);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Agrega un carrito")
    public ResponseEntity<Carrito> createCarrito(@RequestBody Carrito carrito) {
        Carrito createdCarrito = carritoService.saveCarrito(carrito);
        return ResponseEntity.status(201).body(createdCarrito);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un carrito")
    public Carrito updateCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        Carrito existingCarrito = carritoService.getCarritoById(id);
        if (existingCarrito != null) {
            existingCarrito.setCantidad(carrito.getCantidad());
            existingCarrito.setTotal(carrito.getTotal());
            return carritoService.saveCarrito(existingCarrito);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un carrito")
    public void deleteCarrito(@PathVariable Long id) {
        
    }
    
}
