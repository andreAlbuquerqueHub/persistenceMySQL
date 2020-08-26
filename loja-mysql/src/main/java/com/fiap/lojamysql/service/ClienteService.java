package com.fiap.lojamysql.service;

import com.fiap.lojamysql.domain.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();

    Cliente clientePorId(Long id);

    Cliente cadastrar(Cliente cliente);

    void editar(Cliente cliente);

    void excluir(Long id);
}
