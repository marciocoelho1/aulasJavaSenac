<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Funcionarios</title>
</head>

<body>
<h1>Funcionarios da Loja</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Cargo</th>
        <th>Tempo de empresa</th>
        <th>Férias a pagar</th>
    </tr> <!-- Corrigido de <tr/> para </tr> -->

    <c:forEach var="funcionario" items="${funcionarios}">
        <tr>
            <td>${funcionario.id}</td>
            <td>${funcionario.nome}</td>
            <td>${funcionario.cargo}</td>
            <td>${funcionario.tempoEmpresa}</td>
            <td>${funcionario.ferias}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>