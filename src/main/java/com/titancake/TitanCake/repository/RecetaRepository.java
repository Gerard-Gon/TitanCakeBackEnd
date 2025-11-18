package com.titancake.TitanCake.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long>  {
    
}
