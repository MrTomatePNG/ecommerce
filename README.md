# E-commerce API

Uma API REST simples de e-commerce feita com Spring Boot para aprender os padrões de desenvolvimento.

## Objetivo

Criar uma API onde usuários podem:
- Ver produtos disponíveis
- Adicionar produtos ao carrinho
- Fazer pedidos
- Acompanhar status dos pedidos

## Estrutura do Projeto
```
src/main/java/com/example/ecommerce/
├── controller/      (recebe requisições HTTP)
├── service/         (lógica de negócio)
├── repository/      (acessa banco de dados)
├── model/           (entidades @Entity)
├── dto/             (objetos para JSON)
├── exception/       (exceções customizadas)
└── config/          (configurações)
```

## Entidades Principais

- **Produto** - nome, descrição, preço, estoque
- **Usuario** - nome, email, senha
- **Carrinho** - usuario, itens, total
- **Pedido** - usuario, data, status (pendente, pagto, enviado, entregue), total
- **ItemPedido** - produto, quantidade, preço_unitario

## Endpoints a Implementar
```
GET    /produtos              (listar todos)
POST   /usuarios              (cadastrar)
POST   /carrinho/items        (adicionar ao carrinho)
POST   /pedidos               (criar pedido)
GET    /pedidos/{id}          (ver pedido)
GET    /pedidos/usuario/{id}  (histórico do usuário)
```

## Como Rodar
```bash
mvn clean install
mvn spring-boot:run
```

Acessa em `http://localhost:8080`
