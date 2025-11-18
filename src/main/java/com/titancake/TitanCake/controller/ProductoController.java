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

import com.titancake.TitanCake.model.Producto;
import com.titancake.TitanCake.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Muestra una lista de los productos")
    public List<Producto> getAllProductos() {
        return productoService.getAllProducto();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por su id")
    public Producto getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar un producto parcialmente")
    public ResponseEntity<Producto> patchProducto(@PathVariable Long id , @RequestBody Producto parcialProducto){
        try {
            Producto updateProducto = productoService.patchProducto(id, parcialProducto);
            return ResponseEntity.ok(updateProducto);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Agrega un producto")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto createdProducto = productoService.saveProducto(producto);
        return ResponseEntity.status(201).body(createdProducto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existingProducto = productoService.getProductoById(id);
        if (existingProducto != null) {
            existingProducto.setNombreProducto(producto.getNombreProducto());
            existingProducto.setDescripcionProducto(producto.getDescripcionProducto());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());
            return productoService.saveProducto(existingProducto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    public void deleteProducto(@PathVariable Long id) {
        
    }
    
}
