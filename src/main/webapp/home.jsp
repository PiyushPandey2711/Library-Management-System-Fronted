<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.io.*"%>
<%@page import="com.nagarro.entity.Book"%><!DOCTYPE html>
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
			<h2 class="col-2 text-center">Book Listing</h2>
			<form class="d-flex" action="logout" method="post">
				<h5>
					Welcome
					<%=session.getAttribute("username")%>
				</h5>
				<button class="btn btn-outline-danger">Logout</button>
			</form>
		</div>
	</nav>

	<div class="container-fluid">
		<form action="add" method="post">
			<button type="submit" class="btn btn-outline-success mt-5 px-3">AddBook</button>
		</form>
		<!-- table creation -->
		<table class="table table-bordered table-hover table-striped mt-3">
			<thead>
				<tr>
					<th>Book Code</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Data Added</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.getBookCode()}</td>
						<td>${book.getBookName()}</td>
						<td>${book.getAuthor()}</td>
						<td>${book.getDataAddedOn()}</td>
						<td style="display: flex;" class="text-center">

							<form action="edit" method="post">
								<input type="text" name="bookCode" value="${book.getBookCode()}" hidden="true"> 
								<input type="text" name="bookName" value="${book.getBookName()}" hidden="true"> 
								<input
									type="text" name="author" value="${book.getAuthor()}" hidden="true"> 
								<input type="text" name="dataAddedOn" value="${book.getDataAddedOn()}" hidden="true">
								<button type="submit" id="submit-btn" class="btn btn-primary mx-3">Edit</button>
							</form>
							<form action="delete" method="post">
								<input type="text" name="bookCode" value="${book.getBookCode()}" hidden="true">
								<button type="submit" id="submit-btn" class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>