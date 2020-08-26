package com.fiap.lojamysql.service.impl;

import com.fiap.lojamysql.domain.Cliente;
import com.fiap.lojamysql.domain.Produto;
import com.fiap.lojamysql.repository.ClienteRepository;
import com.fiap.lojamysql.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente clientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return (Cliente) cliente.get();
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        verificaProduto(cliente.getId());
        clienteRepository.save(cliente);
    }

    @Override
    public void excluir(Long id) {
        clientePorId(id);
        clienteRepository.deleteById(id);
    }

    private void verificaProduto(Long id){
        clientePorId(id);
    }
}
