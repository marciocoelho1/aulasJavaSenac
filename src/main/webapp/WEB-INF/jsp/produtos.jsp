<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Produtos</title>
</head>

<body>
<h1>Produtos da Loja</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Quantidade</th>
    </tr>

    <c:forEach var="produto" items="${produtos}">
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.descricao}</td>
            <td>${produto.preco}</td>
            <td>${produto.quantidade}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
