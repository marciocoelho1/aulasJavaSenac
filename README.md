# Projeto Loja - Aulas de Java SENAC

Aplicação Web desenvolvida durante as aulas de **Java** e **Spring Boot** no SENAC para gerenciamento de catálogo de produtos e categorias, cobrindo a evolução desde arquiteturas básicas com JDBC até padrões modernos com Spring Data JPA, Hibernate, Thymeleaf e banco de dados relacional MySQL.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+ / 24**
- **Spring Boot** (Spring MVC, Spring Data JPA, Thymeleaf, Validation)
- **Hibernate / JPA** (Mapeamento Objeto-Relacional)
- **MySQL / MySQL Connector**
- **Bootstrap 5** (Layout responsivo e estilização)
- **Maven / Maven Wrapper**

---

## 🧭 Linha do Tempo e Evolução do Projeto por Aulas

O repositório documenta a evolução da disciplina, passando por diferentes abordagens de persistência, arquitetura e interface:

### 📅 Aula 1 (16/09/2026) — Configuração Inicial, JDBC e API REST
* **Foco:** Fundamentos de Spring Web MVC, Spring JDBC e persistência relacional.
* **Persistência:** Configuração de `JdbcTemplate`, `DataSource` e banco de dados em memória **H2**.
* **Modelagem & DAO:**
  * Criação do modelo `Produto` (`id`, `nome`, `preco`).
  * Implementação do padrão **DAO** (`ProdutoDao`) com queries SQL manuais e `RowMapper`.
  * Criação do script de banco `schema.sql`.
* **API:** Criação do `ProdutoController` inicial com endpoints REST para cadastro e listagem.

### 📅 Aula 2 (18/09/2026) — CRUD Completo e Interface de Gestão
* **Foco:** Operações completas de CRUD e desenvolvimento de interface frontend moderna.
* **Operações:** Endpoints REST completos em `/api/produtos`:
  * `POST /api/produtos` (Cadastrar)
  * `GET /api/produtos` (Listar)
  * `PUT /api/produtos/{id}` (Atualizar)
  * `DELETE /api/produtos/{id}` (Excluir)
* **Frontend:**
  * Página de administração interativa `cadastro.html` estilizada com Bootstrap 5.
  * Consumo da API via JavaScript assíncrono (`fetch`), drawer/modal para formulários e suporte a modo escuro/dashboard corporativo.

### 📅 Aula 3 (21/09/2026) — Transição para Spring Data JPA e MySQL
* **Foco:** Migração da camada de dados de JDBC manual para **ORM** (JPA / Hibernate) e banco corporativo.
* **Banco de Dados:** Substituição do H2 pelo **MySQL** (`localhost:3307/loja_senac`), habilitando geração DDL automática (`spring.jpa.hibernate.ddl-auto=update`).
* **Mapeamento JPA:**
  * Transformação de `Produto` em `@Entity` JPA com anotações `@Id`, `@GeneratedValue`, `@Column` e `@Table`.
  * Expansão dos atributos do produto: inclusão de `quantidade` e `descricao`.
  * Limpeza de scripts legados (`schema.sql`).

### 📅 Aula 4 (23/09/2026) — Relacionamentos JPA e Renderização Server-Side com Thymeleaf
* **Foco:** Relacionamentos entre entidades, Spring Data Repositories e renderização com Thymeleaf.
* **Nova Entidade `Categoria`:**
  * Criação de `Categoria` com restrição de unicidade no nome (`uk_categoria_nome`).
  * Mapeamento do relacionamento **1:N** e **N:1** entre `Categoria` e `Produto` (`@ManyToOne` / `@OneToMany`).
* **Spring Data JPA Repositories:**
  * Criação das interfaces `ProdutoRepository` e `CategoriaRepository` estendendo `JpaRepository`.
  * Criação de `ConfiguracaoInicial` com `CommandLineRunner` para carga automática de categorias iniciais (*Informática*, *Escritório*).
* **Interface com Thymeleaf:**
  * Substituição de chamadas REST puras por MVC tradicional server-side renderizado via `@Controller`.
  * Criação dos templates HTML em `src/main/resources/templates/produtos/`:
    * `lista.html`: Listagem de produtos exibindo código, nome, preço, quantidade, categoria e botões de ação.
    * `formulario.html`: Formulário de cadastro de produtos vinculado ao objeto modelo (`th:object`) e com carregamento dinâmico do `<select>` de categorias via Thymeleaf.

---

## 📁 Estrutura Atual do Projeto

```text
loja/
├── src/
│   ├── main/
│   │   ├── java/br/com/loja/
│   │   │   ├── config/
│   │   │   │   └── ConfiguracaoInicial.java   # Carga inicial de dados (CommandLineRunner)
│   │   │   ├── controller/
│   │   │   │   └── ProdutoController.java     # Controlador MVC (Thymeleaf)
│   │   │   ├── model/
│   │   │   │   ├── Categoria.java             # Entidade Categoria (JPA)
│   │   │   │   └── Produto.java               # Entidade Produto (JPA, ManyToOne com Categoria)
│   │   │   ├── repository/
│   │   │   │   ├── CategoriaRepository.java   # Spring Data JPA Repository de Categoria
│   │   │   │   └── ProdutoRepository.java     # Spring Data JPA Repository de Produto
│   │   │   └── LojaApplication.java           # Classe principal (Spring Boot Main)
│   │   └── resources/
│   │       ├── templates/produtos/
│   │       │   ├── formulario.html            # Template Thymeleaf para cadastro/edição
│   │       │   └── lista.html                 # Template Thymeleaf para listagem
│   │       └── application.properties         # Configurações do MySQL, JPA e DataSource
│   └── test/
│       └── java/br/com/loja/
│           └── LojaApplicationTests.java
├── pom.xml
└── README.md
```

---

## 🌐 Rotas e Acesso no Navegador

Com a aplicação em execução na porta padrão (`8080`):

| Rota | Método | Descrição | Template / Resposta |
| :--- | :---: | :--- | :--- |
| `http://localhost:8080/produtos` | `GET` | Listagem de todos os produtos cadastrados | `produtos/lista.html` |
| `http://localhost:8080/produtos/novo` | `GET` | Formulário para cadastro de novo produto | `produtos/formulario.html` |
| `http://localhost:8080/produtos/salvar` | `POST` | Processa o formulário, salva no MySQL e redireciona | Redireciona para `/produtos` |

---

## ⚙️ Como Executar o Projeto

1. **Pré-requisitos:**
   * **Java 17** ou superior (testado com JDK 24).
   * **MySQL** em execução na porta `3307` com o banco `loja_senac` criado:
     ```sql
     CREATE DATABASE IF NOT EXISTS loja_senac;
     ```

2. **Compilar e Rodar:**
   * Usando o Maven Wrapper no terminal:
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```
   * Ou diretamente pelo **IntelliJ IDEA** executando a classe principal [`LojaApplication.java`](file:///src/main/java/br/com/loja/LojaApplication.java).

3. Acesse no navegador:
   * **[http://localhost:8080/produtos](http://localhost:8080/produtos)**
