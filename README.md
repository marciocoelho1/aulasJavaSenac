# Projeto Loja - Aulas Java SENAC

Aplicação Web desenvolvida com **Java 17** e **Spring Boot** para gerenciamento de loja, incluindo listagem de funcionários e cadastro de produtos com persistência em banco de dados H2.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (Spring Web MVC, Spring JDBC)
- **H2 Database** (Banco de dados de arquivo)
- **JSP (JavaServer Pages) & JSTL**
- **Maven**

---

## 📁 Estrutura do Projeto

- `src/main/java/br/com/loja/`
  - **`controller/`**: Endpoints REST e Controllers das visões (`FuncionarioController`, `ProdutoController`).
  - **`dao/`**: Camada de acesso a dados usando `JdbcTemplate` (`ProdutoDao`).
  - **`model/`**: Classes de modelo (`Funcionario`, `Produto`).
- `src/main/resources/`
  - **`schema.sql`**: Script de criação das tabelas no banco de dados H2.
  - **`static/cadastro.html`**: Formulário estático HTML para inclusão de produtos.
- `src/main/webapp/WEB-INF/jsp/`
  - **`funcionarios.jsp`**, **`produtos.jsp`**: Páginas de visualização dos dados.

---

## 🚀 Endpoints da Aplicação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/funcionarios` | Renderiza a página JSP com a listagem de funcionários |
| `POST` | `/api/produtos` | Cadastra um novo produto no banco de dados com validações de nome e preço |
| `GET` | `/cadastro.html` | Página estática para formulário de cadastro |

---

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o Java 17 (ou superior) instalado.
2. Clone o repositório:
   ```bash
   git clone https://github.com/marciocoelho1/aulasJavaSenac.git
   ```
3. Navegue até o diretório do projeto:
   ```bash
   cd aulasJavaSenac
   ```
4. Execute o projeto usando Maven Wrapper:
   - **Windows**:
     ```cmd
     mvnw.cmd spring-boot:run
     ```
   - **Linux / macOS**:
     ```bash
     ./mvnw spring-boot:run
     ```
5. Acesse no navegador:
   - Lista de Funcionários: `http://localhost:8080/funcionarios`
   - Cadastro de Produtos: `http://localhost:8080/cadastro.html`
