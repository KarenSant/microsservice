# Wine Service (Java 23 + Spring Boot)

Microserviço para análise de compras de vinhos baseado em dados de clientes e produtos.

## ✅ Stack

- Java 23
- Spring Boot 2.5.4
- Hexagonal Architecture
- Jackson (leitura de JSON)
- Maven

## 📁 Estrutura Hexagonal
src/main/java/br/com/wineservice/
├── adapter
│   ├── in (Entrada: Controladores e DTOs)
│   │   ├── CompraController.java
│   │   ├── dto
│   │   │   ├── ClienteFielDTO.java
│   │   │   ├── CompraDetalheDTO.java
│   │   │   └── ProdutoDTO.java
│   ├── out (Saída: Implementações de repositórios)
│   │   ├── ClienteRepositoryImpl.java
│   │   ├── CompraRepositoryImpl.java
│   │   └── ProdutoRepositoryImpl.java
├── application (Regras de negócio e casos de uso)
│   ├── service
│   │   └── CompraService.java
├── domain (Núcleo: Modelos e interfaces)
│   ├── model
│   │   ├── ClienteCompra.java
│   │   ├── CompraItem.java
│   │   └── Produto.java
│   ├── port
│   │   ├── in (Interfaces de entrada)
│   │   │   └── CompraUseCase.java
│   │   ├── out (Interfaces de saída)
│   │   │   ├── ClienteRepository.java
│   │   │   ├── CompraRepository.java
│   │   │   └── ProdutoRepository.java


## 📦 Endpoints

| Método | Rota                         | Descrição                                                                 |
|--------|------------------------------|---------------------------------------------------------------------------|
| GET    | `/compras`                   | Lista todas as compras ordenadas por valor total (crescente)             |
| GET    | `/maior-compra/{ano}`        | Retorna a maior compra realizada no ano informado                        |
| GET    | `/clientes-fieis`            | Retorna o top 3 clientes mais fiéis (mais compras e maior valor)         |
| GET    | `recomendacao/cliente/tipo/{cpf}`        | Retorna recomendação de vinho com base nas preferências do cliente       |

📷 Visualização no Insomnia

![Rotas no Insomnia](docs/insomnia_rotas.png)

▶ Como executar

1. Clone o projeto  
   `git clone https://github.com/seu-usuario/wineservice.git`

2. Execute o projeto com Maven ou sua IDE

3. Acesse o serviço em:  
   `http://localhost:8080/api/...`

## 📁 JSON de dados

Os dados estão em `src/main/resources/json/`:
- `produtos.json`
- `clientes.json`

## ▶️ Execução

1. Configure variáveis de ambiente via `.env`
2. Execute `./start.sh`
