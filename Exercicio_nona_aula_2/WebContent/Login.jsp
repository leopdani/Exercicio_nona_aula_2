<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="pt-br">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>

<body>
	<c:import url="Menu.jsp" />

	<div id="main" class="container">
		<h3 class="page-header">Países</h3>

		<form class="form-group" action="LoginController.do" method="post">
			<div class="row">
				<div class="col-md-4">
					Usuario:<input class="form-control col-md-4" type="text"
						name="login">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					Senha: <input class="form-control" type="password" name="senha">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<button type="submit">Entrar</button>
					Não é um usuário? 
					<a href="cadastrar.jsp">Cadastre-se.</a>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>