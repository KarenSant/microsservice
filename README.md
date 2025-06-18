# Wine Service (Java 23 + Spring Boot)

Microserviço para análise de compras de vinhos baseado em dados de clientes e produtos.

## ✅ Stack

- Java 23
- Spring Boot 3.x
- Hexagonal Architecture
- Jackson (leitura de JSON)
- Maven

## 📁 Estrutura Hexagonal


## 📦 Endpoints

| Método | Rota                         | Descrição                                                                 |
|--------|------------------------------|---------------------------------------------------------------------------|
| GET    | `/compras`                   | Lista todas as compras ordenadas por valor total (crescente)             |
| GET    | `/maior-compra/{ano}`        | Retorna a maior compra realizada no ano informado                        |
| GET    | `/clientes-fieis`            | Retorna o top 3 clientes mais fiéis (mais compras e maior valor)         |
| GET    | `/recomendacao/{cpf}`        | Retorna recomendação de vinho com base nas preferências do cliente       |

## 📁 JSON de dados

Os dados estão em `src/main/resources/json/`:
- `produtos.json`
- `clientes.json`

## ▶️ Execução

1. Configure variáveis de ambiente via `.env`
2. Execute `./start.sh`
