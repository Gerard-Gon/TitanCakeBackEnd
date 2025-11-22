package com.titancake.TitanCake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

import com.titancake.TitanCake.model.Categoria;
import com.titancake.TitanCake.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/categorias") 
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping
    @Operation(summary = "Lista todas las categorías")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias); 
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una categoría por ID")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(categoria); 
    }

   
    @PostMapping
    @Operation(summary = "Agrega una nueva categoría")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        
        categoria.setId(null); 
        Categoria createdCategoria = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }

    
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoría completamente")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria updatedCategoria = categoriaService.save(categoria);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategoria); 
    }
    
    
    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza una categoría parcialmente (ej. solo el nombre)")
    public ResponseEntity<Categoria> updatePartialCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria updatedCategoria = categoriaService.partialUpdate(categoria);
        if (updatedCategoria == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(updatedCategoria); 
    }
    
    
}