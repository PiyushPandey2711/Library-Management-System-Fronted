<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.nagarro.entity.Author"%>
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
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if (session.getAttribute("username") == null)
		response.sendRedirect("login.jsp");
	%>

	<nav class="navbar bg-body-tertiary">
		<div class="container-fluid">
			<h2>Book Listing</h2>
			<form class="d-flex" action="logout" method="post">
				<h5>
					Welcome
					<%=session.getAttribute("username")%>
				</h5>
				<button class="btn btn-outline-danger">Logout</button>
			</form>
		</div>
	</nav>

	<div class="container-fluid mt-5 mx-5">
		<div class="row">
			<div id="title" style="text-align: center;">
				<h3>Edit Book Details</h3>
			</div>
			<div class="main-content col-6">
				<form action="editBook" method="post">
					<div class="mb-3 row">
						<label for="bookCode" class="col-3 mx-3">Book Code</label> <input
							type="text" class="col-4" id="bookCode"
							value="<%=request.getParameter("bookCode")%>" name="bookCode"
							readonly>

					</div>
					<div class="mb-3 row">
						<label for="bookName" class="col-3 mx-3">Book Name</label> <input
							type="text" class="col-4" id="bookName"
							value="<%=request.getParameter("bookName")%>" name="bookName"
							required>

					</div>
					<div class="mb-3 row">
						<label for="author" class="col-3 mx-3">Author</label> <select
							class="col-4" name="author" id="author" required>
							<c:forEach items="${Author}" var="Author">
								<option value="${Author.getAuthorName()}">${Author.getAuthorName()}</option>
							</c:forEach>
						</select>

					</div>
					<div class="mb-3 row">
						<label for="dataAddedOn" class="col-3 mx-3">Data Added On</label>
						<input type="text" id="dataAddedOn" class="col-4"
							value="<%=request.getParameter("dataAddedOn")%>"
							name="dataAddedOn" readonly>
					</div>

					<div class="col-12 mx-4">
						<button type="submit" class="btn btn-outline-success mx-3">Save</button>
						<button type="submit" class="btn btn-outline-danger mx-3">
							<a href="home.jsp" style="text-decoration: none;">Cancel</a>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>