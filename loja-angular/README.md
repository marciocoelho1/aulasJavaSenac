# Aulas Java Senac

Repositório de atividades desenvolvidas durante as aulas do curso de Java no Senac.

## Classificação das aulas

As atividades são identificadas pelo tipo de conteúdo e pelo tema desenvolvido. Este projeto está classificado como:

- **Aula prática — Angular:** aplicação de uma loja para cadastro, edição, listagem e exclusão de produtos.

## Projeto: Loja Senac

Aplicação front-end desenvolvida com Angular para gerenciar produtos. O formulário utiliza formulários reativos e a comunicação com os dados é centralizada no serviço de produtos.

### Executar localmente

Instale as dependências e inicie o servidor de desenvolvimento:

```bash
npm install
npm start
```

A aplicação ficará disponível em `http://localhost:4200/`.

### Comandos úteis

```bash
npm run build  # compila a aplicação
npm test       # executa os testes
```

## Estrutura principal

- `src/app/models/`: modelos de produto e categoria.
- `src/app/services/`: serviço de acesso aos produtos.
- `src/app/`: componente principal da aplicação.
