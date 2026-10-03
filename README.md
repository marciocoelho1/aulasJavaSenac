# Projeto Loja — Aulas de Java SENAC

Aplicação desenvolvida nas aulas de Java do SENAC para acompanhar a evolução de um sistema de catálogo: dos fundamentos de JDBC e API REST até persistência com Spring Data JPA, relacionamentos entre entidades, páginas Thymeleaf e objetos DTO.

---

## Tecnologias utilizadas

- Java 17;
- Spring Boot, Spring MVC e Spring Data JPA;
- Hibernate e Jakarta Validation;
- MySQL 8;
- Thymeleaf, HTML, CSS e Bootstrap;
- Maven e Maven Wrapper.

---

## Linha do tempo e evolução por aulas

O projeto reúne os conceitos praticados em etapas diferentes da disciplina. A aplicação atual combina essas etapas em uma estrutura única, na raiz do repositório.

### Aula 1 (16/09/2026) — Configuração inicial, JDBC e API REST

- Configuração inicial do Spring Web e da conexão com banco de dados;
- Introdução a `DataSource`, `JdbcTemplate`, SQL e ao padrão DAO;
- Primeiras rotas REST para cadastrar e listar produtos.

### Aula 2 (18/09/2026) — CRUD e interface de gestão

- Operações de criação, consulta, atualização e exclusão;
- Consumo de rotas REST pelo navegador;
- Construção de uma interface de gestão para os produtos.

### Aula 3 (21/09/2026) — Spring Data JPA e MySQL

- Migração da persistência manual para JPA e Hibernate;
- Mapeamento da entidade `Produto` e configuração do MySQL;
- Uso dos repositórios Spring Data para consultar e persistir dados.

### Aula 4 (23/09/2026) — Relacionamentos e páginas com Thymeleaf

- Criação da entidade `Categoria` e relacionamento com `Produto` (`@ManyToOne` e `@OneToMany`);
- Repositórios e serviços para organizar o acesso aos dados e as regras da aplicação;
- Formulários e listagens renderizados no servidor com Thymeleaf;
- Validação dos dados e carga inicial de categorias.

### Continuação — DTOs e integração das interfaces

- O pacote `dto` define os dados recebidos e enviados pela API sem expor diretamente as entidades JPA;
- `ProdutoRequest` valida os dados de entrada e `ProdutoResponse` representa a resposta, incluindo os dados da categoria;
- `api.ProdutoApiController` disponibiliza consulta e cadastro de produtos em `/api/produtos`;
- `controller.ProdutoController` e `CategoriaController` atendem as páginas HTML em `/produtos` e `/categorias`;
- As duas interfaces compartilham serviços e repositórios, relacionando o exercício inicial de API REST às camadas de persistência e apresentação estudadas depois.

---

### Continuação (02/10/2026) — CRUD completo na API e integração com frontend

- Renomeação do controlador REST para `ProdutoApiController`, distinguindo-o do controlador das páginas Thymeleaf;
- Atualização de produtos com `PUT /api/produtos/{id}` e exclusão com `DELETE /api/produtos/{id}`, com resposta `204 No Content` na exclusão;
- Uso de `ProdutoRequest`, `ProdutoResponse` e `CategoriaResponse` para transportar e validar dados;
- Introdução ao tratamento de erros com `ProblemDetail` em `ApiExceptionHandler`;
- Introdução à configuração de CORS para o frontend em `http://localhost:4200`;
- Execução do MySQL 8.4 com Docker Compose, volume persistente e verificação de saúde.

Pontos para revisar na continuidade da aula: o `ApiExceptionHandler` está limitado ao pacote `br.com.senac.loja.controller.api`, enquanto o controlador REST está em `br.com.senac.loja.api`; o padrão CORS `/api**` precisa ser ajustado para `/api/**` para abranger as rotas de produtos. Essas configurações ainda precisam de validação integrada com o frontend.

---

## Funcionalidades atuais

- Cadastro, edição, listagem e exclusão de produtos pela interface web;
- Cadastro, edição, listagem e exclusão de categorias;
- Associação de produtos a categorias;
- Validação de formulários, mensagens de retorno e proteção contra exclusão de categorias utilizadas;
- Consulta, cadastro, atualização e exclusão de produtos pela API REST;
- Criação automática das tabelas pelo Hibernate e carga inicial das categorias Informática e Escritório.

## Rotas

Com a aplicação em execução na porta `8080`:

| Rota | Método | Uso |
|---|---|---|
| `/produtos` | GET | Lista os produtos na interface web |
| `/produtos/novo` | GET | Abre o formulário de produto |
| `/categorias` | GET | Lista as categorias na interface web |
| `/categorias/nova` | GET | Abre o formulário de categoria |
| `/api/produtos` | GET | Retorna os produtos em JSON |
| `/api/produtos/{id}` | GET | Retorna um produto em JSON |
| `/api/produtos` | POST | Cadastra um produto a partir de JSON |
| `/api/produtos/{id}` | PUT | Atualiza um produto a partir de JSON |
| `/api/produtos/{id}` | DELETE | Exclui um produto e retorna 204 |

## Como executar

### Requisitos

- JDK 17 ou superior;
- Docker Desktop com Docker Compose, ou uma instalação local do MySQL 8.

### Obter o projeto

```bash
git clone https://github.com/marciocoelho1/aulasJavaSenac.git
cd aulasJavaSenac
```

### Banco com Docker

O `docker-compose.yml` inicia o MySQL 8.4 na porta `3306`, com banco `loja_senac`, usuário `root` e senha `root`:

```bash
docker compose up -d
docker compose ps
```

O serviço `mysql` usa o container `loja-senac-mysql`, persiste os dados no volume `loja_senac_mysql_data` e verifica a disponibilidade com `mysqladmin ping`. Aguarde o estado `healthy` antes de iniciar a aplicação. A porta `3306` precisa estar livre. Para parar os serviços mantendo os dados, execute `docker compose down`.

A configuração padrão em `src/main/resources/application.properties` aponta para `localhost:3307` e senha vazia. Para usar o container acima, defina as configurações Spring antes de iniciar.

PowerShell:

```powershell
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/loja_senac"
$env:SPRING_DATASOURCE_USERNAME = "root"
$env:SPRING_DATASOURCE_PASSWORD = "root"
.\mvnw.cmd spring-boot:run
```

Para uma instalação local, ajuste URL, usuário e senha em `application.properties`. O arquivo `banco.sql` cria o banco; as tabelas são geradas pelo Hibernate.

### Abrir no IntelliJ IDEA

1. Abra a pasta do repositório no IntelliJ IDEA e importe o `pom.xml`.
2. Inicie o MySQL e configure a conexão.
3. Execute `br.com.senac.loja.LojaApplication`.
4. Acesse <http://localhost:8080>.

## Estrutura do projeto

```text
src/main/java/br/com/senac/loja/
├── api/          # API REST
├── config/       # Carga inicial e configuração de CORS
├── controller/   # Páginas web MVC
├── dto/          # Contratos de entrada e saída da API
├── form/         # Dados e validação dos formulários
├── model/        # Entidades Produto e Categoria
├── repository/   # Repositórios Spring Data JPA
└── service/      # Regras da aplicação
```

Para fins didáticos, `spring.jpa.hibernate.ddl-auto=update` mantém o esquema alinhado às entidades. Em projetos de produção, alterações de esquema costumam ser controladas por ferramentas de migração.
