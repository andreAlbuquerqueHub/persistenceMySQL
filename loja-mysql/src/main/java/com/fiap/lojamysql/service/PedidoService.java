package com.fiap.lojamysql.service;

import com.fiap.lojamysql.domain.Pedido;

import java.util.List;

public interface PedidoService {

    List<Pedido> listarPedidos();

    Pedido cadastrar(Pedido pedido);

    void deletar(Long id);

    Pedido buscarPedidoPorId(Long id);
}
