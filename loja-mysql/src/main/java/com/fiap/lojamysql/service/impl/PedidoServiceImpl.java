package com.fiap.lojamysql.service.impl;

import com.fiap.lojamysql.domain.Cliente;
import com.fiap.lojamysql.domain.ItemPedido;
import com.fiap.lojamysql.domain.Pedido;
import com.fiap.lojamysql.domain.Produto;
import com.fiap.lojamysql.repository.ClienteRepository;
import com.fiap.lojamysql.repository.PedidoRepository;
import com.fiap.lojamysql.repository.ProdutoRepository;
import com.fiap.lojamysql.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private final String orderNotFund = "O pedido não foi encontrado.";

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido cadastrar(Pedido pedido) {
        List<Produto> produtos = new ArrayList<>();
        Double valorTotal = 0.0;

        try{
            for(ItemPedido item : pedido.getItemPedidos()){
                Produto produto = (Produto) produtoRepository.findById(item.getProduto().getId()).get();
                produtos.add(produto);
            }

            Cliente cliente = (Cliente) clienteRepository.findById(pedido.getCliente().getId()).get();
            pedido.setCliente(cliente);
            pedido.setItemPedidos(new HashSet<>());

            for (Produto prod : produtos){
                pedido.addProduto(prod);
                valorTotal+=prod.getValor();
            }

            pedido.setValorTotal(valorTotal);
            pedidoRepository.save(pedido);
        }catch (Exception e){
            throw new NullPointerException(orderNotFund);
        }
        return pedido;
    }

    @Override
    public void deletar(Long id) {
        try{
            pedidoRepository.deleteById(id);
        }catch (Exception e){
            throw new NullPointerException(orderNotFund);
        }
    }

    @Override
    public Pedido buscarPedidoPorId(Long id) {
        Pedido pedido = new Pedido();
        try {
            pedido = (Pedido) pedidoRepository.findById(id).get();
        }catch (Exception e) {
            throw new NullPointerException(orderNotFund);
        }
        return pedido;
    }
}
