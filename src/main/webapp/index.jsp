<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body class="bg-light">
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3 mt-2">
				<div class="card">
					<div class="card-header">
						<h3>Login</h3>
					</div>
					<form method="post" action = "login">
						<div class="card-body">
							<div class="mb-3">
								<label for="email" class="form-label">User Id : </label>
								<input type="text" class="form-control" name="username" id="username"/>
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password : </label>
								<input type="password" class="form-control" name="password" id="password"/>
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-primary">Login</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>