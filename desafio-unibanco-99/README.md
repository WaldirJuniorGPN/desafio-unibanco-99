# Desafio Unibanco 99

## Descrição
Este projeto é uma resolução para o Desafio do Itaú Unibanco, implementando um sistema de transações financeiras com estatísticas. Ele fornece uma API RESTful que permite o registro de transações, remoção de todas as transações e o cálculo de estatísticas das transações realizadas nos últimos 60 segundos.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.4.0
- Maven
- Lombok
- Jakarta Validation
- OpenAPI (Swagger) para documentação
- JUnit para testes

## Como Executar o Projeto

### 1. Clonar o Repositório
```sh
$ git clone https://github.com/seu-usuario/desafio-unibanco-99.git
$ cd desafio-unibanco-99
```

### 2. Construir o Projeto
Certifique-se de que possui o Maven instalado e execute:
```sh
$ mvn clean install
```

### 3. Executar a Aplicação
```sh
$ mvn spring-boot:run
```
A aplicação será iniciada na porta **8080**.

## Endpoints da API

### Criar uma Transação
**POST** `/transacao`
#### Request Body (JSON):
```json
{
  "valor": 100.50,
  "dataHora": "2025-03-01T10:15:30Z"
}
```
#### Respostas:
- **201 Created**: Transação criada com sucesso.
- **400 Bad Request**: Dados inválidos na requisição.

### Deletar todas as Transações
**DELETE** `/transacao`
#### Respostas:
- **200 OK**: Todas as transações foram removidas.

### Consultar Estatísticas das Transações
**GET** `/transacao/estatistica`
#### Respostas:
- **200 OK**: Retorna um JSON com soma, média, mínimo, máximo e quantidade de transações nos últimos 60 segundos.
- **204 No Content**: Nenhuma transação encontrada para cálculo.

#### Exemplo de Resposta:
```json
{
  "quantidade": 5,
  "soma": 500.00,
  "media": 100.00,
  "minimo": 50.00,
  "maximo": 150.00
}
```

## Documentação com Swagger
Após iniciar a aplicação, a documentação da API pode ser acessada via:
```
http://localhost:8080/swagger-ui.html
```


## Autor
Desenvolvido por Waldir Chagas Leite Júnior.