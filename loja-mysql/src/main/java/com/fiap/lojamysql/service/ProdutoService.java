package com.fiap.lojamysql.service;

import com.fiap.lojamysql.domain.Produto;

import java.util.List;

public interface ProdutoService {

    List<Produto> listar();

    Produto produtoPorId(Long id);

    Produto cadastrar(Produto produto);

    void editar(Produto produto);

    void excluir(Long id);

}
