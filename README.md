#Loja
###loja-mysql foi construída para cadastrar:

 - Clientes
 - Produtos
 - Pedidos

**Procedimento para realizar os cadastros na api**
 - URL Base - localhost:8080/

**Clientes - listar, listar por id, cadastrar, editar e excluir:**

**URLs:**
 - POST e GET - **localhost:8080/clientes**
 - PUT, DELETE, GET : **localhost:8080/clientes/1**

 - Exemplo de body para cadastrar(POST) um novo cliente:
```
{
    "nome": "Daniel",
    "email": "daniel@hotmail.com"
}
```
**Produtos - listar, listar por id, cadastrar, editar e excluir:**

**URLs:**
 - POST e GET - **localhost:8080/produtos**
 - PUT, DELETE, GET : **localhost:8080/produtos/1**
 
 - Exemplo de body para cadastrar(POST) um novo produto:
```
1º cadastro
{
   "descricao":"Produto 1",
   "quantidade": 10,
   "valor": 10.00
}

2º cadastro
{
   "descricao":"Produto 2",
   "quantidade": 5,
   "valor": 5.00
}
```
**Pedidos - cadastrar, deletar e listar por id**

**URLs:**
 - POST e GET - **localhost:8080/pedidos**
 - GET, PUT e DELETE - **localhost:8080/pedidos/1**
 
  - Exemplo de body para cadastrar(POST) um novo pedido:
```
{
	"cliente": {"id": 1},
	"descricao": "Pedido 1",
	"itemPedidos":[{
	   "produto":{"id":1}
	},
    {
	   "produto":{"id":2}
	}]
}
```
*Response do pedido cadastrado:*
```
{
    "id": 1,
    "itemPedidos": [
        {
            "id": 1,
            "produto": {
                "id": 2,
                "descricao": "Produto 2",
                "quantidade": 5,
                "valor": 5
            }
        },
        {
            "id": 2,
            "produto": {
                "id": 1,
                "descricao": "Produto 1",
                "quantidade": 10,
                "valor": 10
            }
        }
    ],
    "cliente": {
        "id": 1,
        "nome": "Daniel",
        "email": "daniel@hotmail.com"
    },
    "valorTotal": 15
}
```
