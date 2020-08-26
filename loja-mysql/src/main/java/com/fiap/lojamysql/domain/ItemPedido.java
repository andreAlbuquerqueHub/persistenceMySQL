package com.fiap.lojamysql.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemPedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonInclude(Include.NON_NULL)
    private Pedido pedido;

    public ItemPedido(Produto produto){
        this.produto = produto;
    }
}
