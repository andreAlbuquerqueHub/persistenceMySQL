package com.fiap.lojamysql.resource;

import com.fiap.lojamysql.domain.Produto;
import com.fiap.lojamysql.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.produtoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
        produtoService.cadastrar(produto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProduto(@RequestBody Produto produto,
                                               @PathVariable("id") Long id){

        produto.setId(id);
        produtoService.editar(produto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable("id") Long id){
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
