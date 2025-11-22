package com.titancake.TitanCake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titancake.TitanCake.model.Categoria;
import com.titancake.TitanCake.repository.CategoriaRepository; 

import jakarta.transaction.Transactional;

@Service
@Transactional // Se mantiene para asegurar atomicidad en save y partialUpdate
@SuppressWarnings("null")
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        return categoria;
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria partialUpdate(Categoria categoria){
        Categoria existingCategoria = categoriaRepository.findById(categoria.getId()).orElse(null);
        if (existingCategoria != null) {
            // Se actualiza el nombre si se proporciona
            if (categoria.getNombreCategoria() != null) {
                existingCategoria.setNombreCategoria(categoria.getNombreCategoria());
            }

            return categoriaRepository.save(existingCategoria);
        }
        return null;
    }

}