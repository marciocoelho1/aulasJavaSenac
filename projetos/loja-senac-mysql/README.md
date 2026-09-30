# Loja Senac

Aplicação didática desenvolvida nas aulas de Java do SENAC para praticar Spring Boot, Spring Data JPA, Hibernate, Thymeleaf e MySQL. O sistema oferece cadastro e manutenção de produtos e categorias.

## Funcionalidades

- Cadastro, edição, listagem e exclusão de produtos;
- Cadastro, edição, listagem e exclusão de categorias;
- Associação de produtos a categorias;
- Validação dos formulários e mensagens de retorno;
- Proteção contra exclusão de categorias que ainda possuem produtos;
- Carga inicial das categorias Informática e Escritório.

## Requisitos

- JDK 17 ou superior;
- Maven 3.9 ou Maven integrado ao IntelliJ IDEA;
- MySQL 8 ou Docker Desktop.

## Banco de dados

O arquivo `docker-compose.yml` inicia o MySQL 8.4 na porta `3306`, com banco `loja_senac`, usuário `root` e senha `root`:

```bash
docker compose up -d
```

A configuração padrão da aplicação, em `src/main/resources/application.properties`, aponta para MySQL na porta `3307` e senha vazia. Para usar o container do Docker na porta `3306`, configure as propriedades Spring ao iniciar:

```powershell
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/loja_senac"
$env:SPRING_DATASOURCE_PASSWORD = "root"
mvn spring-boot:run
```

No Bash, use `export` para definir as mesmas variáveis antes de executar o Maven. Para um MySQL instalado localmente, ajuste a URL, o usuário e a senha em `application.properties`. O script `banco.sql` também pode ser executado no MySQL Workbench.

## Executar

Na pasta deste projeto:

```bash
mvn spring-boot:run
```

Ou importe o `pom.xml` no IntelliJ IDEA e execute `br.com.senac.loja.LojaApplication`.

## Acesso

- Início: <http://localhost:8080>
- Produtos: <http://localhost:8080/produtos>
- Categorias: <http://localhost:8080/categorias>

## Estrutura

```text
src/main/java/br/com/senac/loja/
├── config/       # Dados iniciais
├── controller/   # Rotas e páginas
├── dto/          # Objetos de resposta e requisição
├── form/         # Dados e validação dos formulários
├── model/        # Entidades Produto e Categoria
├── repository/   # Repositórios Spring Data JPA
└── service/      # Regras da aplicação
```

O Hibernate está configurado com `spring.jpa.hibernate.ddl-auto=update` para facilitar as aulas. Para executar a aplicação, mantenha um banco MySQL disponível e configure as credenciais correspondentes.
