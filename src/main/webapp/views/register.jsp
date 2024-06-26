<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulário de Cadastro</title>
<!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">  -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="icon" type="image/png"
	href="https://cdn-icons-png.flaticon.com/512/10384/10384161.png">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<style>
body {
	background-color: #454d6b;
}
</style>
</head>
<body>
	<jsp:include page="../component/navbar.jsp" />

	<erro:message attribute="mensagemErro"></erro:message>

	

	<div class="container mt-5">
		<div
			class="row d-flex justify-content-center align-items-center h-100">
			<div class="col-12 col-md-10 col-lg-8 col-xl-7">
				<div class="card bg-dark text-white" style="border-radius: 1rem;">
					<div class="card-body p-5 text-center">
						<jsp:include page="formCadastro.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>