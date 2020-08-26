package com.fiap.lojamysql.service.impl;

import com.fiap.lojamysql.domain.Produto;
import com.fiap.lojamysql.repository.ProdutoRepository;
import com.fiap.lojamysql.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl  implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto produtoPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return (Produto) produto.get();
    }

    @Override
    public Produto cadastrar(Produto produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    @Override
    public void editar(Produto produto) {
        verificaProduto(produto.getId());
        produtoRepository.save(produto);
    }

    @Override
    public void excluir(Long id) {
        produtoPorId(id);
        produtoRepository.deleteById(id);
    }

    private void verificaProduto(Long id){
        produtoPorId(id);
    }
}
