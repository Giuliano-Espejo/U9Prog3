package com.gestion.pedidos.dto.producto;

import com.gestion.pedidos.entity.Categoria;
import com.gestion.pedidos.entity.Producto;

import java.math.BigDecimal;

public record ProductoCreate(
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Long categoriaId
) {
    public Producto toEntity() {
        return Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .build();
    }

}
