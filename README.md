# Projeto Loja - Aulas de Java SENAC

Aplicação Web desenvolvida com **Java** e **Spring Boot** para gerenciamento de produtos (CRUD completo), integrada com a camada de acesso a dados DAO (`JdbcTemplate`), banco de dados H2 e uma interface HTML/JavaScript interativa com Bootstrap.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Spring Web MVC, Spring JDBC)
- **H2 Database** (Banco de Dados relacional em arquivo)
- **Bootstrap 5** & **JavaScript (Fetch API)**
- **Maven**

---

## 📁 Estrutura do Projeto

- `src/main/java/br/com/loja/`
  - **`controller/`**: [ProdutoController.java](src/main/java/br/com/loja/controller/ProdutoController.java) - Endpoints REST da aplicação (`GET`, `POST`, `PUT`, `DELETE`).
  - **`dao/`**: [ProdutoDao.java](src/main/java/br/com/loja/dao/ProdutoDao.java) - Camada de Acesso a Dados utilizando `JdbcTemplate`.
  - **`model/`**: [Produto.java](src/main/java/br/com/loja/model/Produto.java) - Modelo representando o Produto (`id`, `nome`, `preco`).
- `src/main/resources/`
  - **`schema.sql`**: Script de criação da tabela no banco de dados H2 com Chave Primária (`PRIMARY KEY`).
  - **`static/cadastro.html`**: Interface gráfica com menu suspenso para executar as operações de **Cadastrar**, **Listar**, **Atualizar** e **Excluir**.

---

## 🚀 Endpoints da API REST (`/api/produtos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/produtos` | Cadastra um novo produto |
| `GET` | `/api/produtos` | Retorna a lista de todos os produtos cadastrados |
| `PUT` | `/api/produtos/{id}` | Atualiza o produto com o `ID` especificado |
| `DELETE` | `/api/produtos/{id}` | Remove o produto com o `ID` especificado |

---

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Java 17** (ou superior) instalado.
2. Clone o repositório:
   ```bash
   git clone https://github.com/marciocoelho1/aulasJavaSenac.git
   ```
3. Navegue até o diretório do projeto:
   ```bash
   cd loja
   ```
4. Execute o projeto usando o Maven Wrapper:
   - **Windows**:
     ```cmd
     mvnw.cmd spring-boot:run
     ```
   - **Linux / macOS**:
     ```bash
     ./mvnw spring-boot:run
     ```
5. Acesse no seu navegador a página principal de gerenciamento:
   - **`http://localhost:8080/cadastro.html`**
