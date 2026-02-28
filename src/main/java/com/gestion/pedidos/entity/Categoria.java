package com.gestion.pedidos.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Categoria extends Base {

    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    Set<Producto> productos = new HashSet<>();

    public void addProducto(Producto producto){
        productos.add(producto);
    }
}
