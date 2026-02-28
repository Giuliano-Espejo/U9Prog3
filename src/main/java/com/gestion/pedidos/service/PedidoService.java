package com.gestion.pedidos.service;

import com.gestion.pedidos.dto.pedido.PedidoCreate;
import com.gestion.pedidos.dto.pedido.PedidoDto;

import java.util.List;

public interface PedidoService {

    PedidoDto crearPedido(PedidoCreate dto);

    PedidoDto obtenerPedidoPorId(Long id);

    List<PedidoDto> listarPedidos();

    void eliminarPedido(Long id);
}