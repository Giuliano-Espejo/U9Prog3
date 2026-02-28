package com.gestion.pedidos.dto.detallepedido;

import com.gestion.pedidos.entity.DetallePedido;
import com.gestion.pedidos.entity.Producto;


public record DetallePedidoCreate(
        Integer cantidad,
        Long productoId
) {
}
