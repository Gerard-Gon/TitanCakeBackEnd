package com.titancake.TitanCake.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.titancake.TitanCake.model.Receta;
import com.titancake.TitanCake.repository.RecetaRepository;

@Service
public class RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> getAllRecetas() {
    return recetaRepository.findAll();
    }
    public Receta getRecetaById(Long id) {
        return recetaRepository.findById(id).orElse(null);
    }

    public Receta saveReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void deleteReceta(Long id) {
        recetaRepository.deleteById(id);
    }

public Receta patchReceta(Long id, Receta parcialReceta){
        Optional<Receta> recetaOptional = recetaRepository.findById(id);
        if (recetaOptional.isPresent()) {

            Receta recetaToUpdate = recetaOptional.get();

            if (parcialReceta.getNombreReceta()!=null) {
                recetaToUpdate.setNombreReceta(parcialReceta.getNombreReceta());
            }
            if (parcialReceta.getDescripcionReceta()!=null) {
                recetaToUpdate.setDescripcionReceta(parcialReceta.getDescripcionReceta());
            }
            return recetaRepository.save(recetaToUpdate);
        } else {
            return null; // or throw an exception
        }

    }
    
}
