package com.titancake.TitanCake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    
}
