package com.titancake.TitanCake.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="producto")


public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50,nullable = false)
    private String nombreProducto;

    @Column(nullable = false)
    private Integer precio;

    @Column(length = 500,nullable = true)
    private String descripcionProducto;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable=true)//Esta era mi idea para poner una imagen, no entendi muy bien lo de crear otra tabla
    private String imageUrl;
}
