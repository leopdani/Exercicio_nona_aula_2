<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<body>
	<c:import url="Menu.jsp" />

	<div id="main" class="container">
		<h3 class="page-header">Países</h3>

		<form class="form-group" action="CadastrarController.do" method="post">
			<div class="row">
				<div class="col-md-4">
					Login:<input class="form-control col-md-4" type="text" name="login">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					Nome:<input class="form-control col-md-4" type="text" name="nome">
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					Senha: <input class="form-control" type="password" name="senha">
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<button type="submit">Criar</button>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>