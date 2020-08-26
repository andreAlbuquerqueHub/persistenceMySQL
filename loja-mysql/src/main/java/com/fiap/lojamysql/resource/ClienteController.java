package com.fiap.lojamysql.resource;

import com.fiap.lojamysql.domain.Cliente;
import com.fiap.lojamysql.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarPorId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.clientePorId(id));
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrar(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@RequestBody Cliente cliente,
                                                 @PathVariable("id") Long id){

        cliente.setId(id);
        clienteService.editar(cliente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable("id") Long id){
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
