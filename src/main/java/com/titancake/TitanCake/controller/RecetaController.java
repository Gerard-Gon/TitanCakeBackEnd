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

import com.titancake.TitanCake.model.Receta;
import com.titancake.TitanCake.service.RecetaService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/recetas")
public class RecetaController {
    //Aca me falta agregar el responseEntity en todo, 
    // pero aun no esta hecho por que no afecta a ninguna de las funcionalidades mas importantes del front.
    @Autowired
    private RecetaService recetaService;

    @GetMapping
    @Operation(summary = "Muestra una lista de las recetas registradas")
    public List<Receta> getAllURecetas() {
        return recetaService.getAllRecetas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener receta por su id")
    public Receta getRecetaById(@PathVariable Integer id) {
        return recetaService.getRecetaById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar una receta parcialmente")
    public ResponseEntity<Receta> patchReceta(@PathVariable Integer id , @RequestBody Receta parcialReceta){
        try {
            Receta updateReceta = recetaService.patchReceta(id, parcialReceta);
            return ResponseEntity.ok(updateReceta);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //En caso de error revisar esta parte
    @PostMapping
    @Operation(summary = "Agrega una receta")
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        Receta createdReceta = recetaService.saveReceta(receta);
        return ResponseEntity.status(201).body(createdReceta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una receta")
    public Receta updateReceta(@PathVariable Integer id, @RequestBody Receta receta) {
        Receta existingReceta = recetaService.getRecetaById(id);
        if (existingReceta != null) {
            existingReceta.setNombreReceta(receta.getNombreReceta());
            existingReceta.setDescripcionReceta(receta.getDescripcionReceta());
            return recetaService.saveReceta(existingReceta);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una receta")
    public void deleteReceta(@PathVariable Long id) {
        
    }






    
}
