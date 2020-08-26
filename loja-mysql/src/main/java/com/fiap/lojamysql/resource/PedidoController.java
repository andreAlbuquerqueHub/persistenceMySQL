package com.fiap.lojamysql.resource;

import com.fiap.lojamysql.domain.Pedido;
import com.fiap.lojamysql.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping()
    public ResponseEntity<List<Pedido>> listarPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listarPedidos());
    }

    @PostMapping
    public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido){
        pedidoService.cadastrar(pedido);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> deletarPedido(@PathVariable("id") Long id){
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable("id")Long id){
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }
}