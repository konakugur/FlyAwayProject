<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<style>
		.error{
			color:red;
		}
	</style>

</head>
<body>

	<div>
	<%
		String errMsg = (String) request.getAttribute("errorMessage");
		if(errMsg != null){
		%>
		<span class='error'><%= errMsg %></span>
			<%} %>
	</div>
	
	<div class='container'>
		<form action='login' method=POST>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Connection
					Url</label> <input type="text" class="form-control" name="url"
					value="jdbc:mysql://localhost:3306/hibernate_db?useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">Don't leave this blank.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Driver
					Url</label> <input type="text" class="form-control" name="driver"
					value="com.mysql.cj.jdbc.Driver" id="exampleInputEmail2"
					aria-describedby="emailHelp">
				<div id="emailHelp" class="form-text">Don't leave this blank.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Username</label>
				<input type="text" class="form-control" name="username" value="root"
					id="exampleInputPassword1">
			</div>
			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">Password</label>
				<input type="password" class="form-control" name="password"
					value="Ue130516Eu*12" id="exampleInputPassword2">
			</div>
			<input type="checkbox" name="adminCheckbox" id="adminCheckbox" value="adminCheckbox" />Login As Admin
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Admin Name
					Url</label> <input type="text" class="form-control" name="adminName"
					value="admin"
					id="exampleInputEmail1" aria-describedby="emailHelp">
				<div id="adminNameHelp" class="form-text">Fill this area if you want to login as admin.</div>
			</div>
			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Admin
					Password</label> <input type="password" class="form-control" name="adminPassword"
					value="admin"
					id="exampleInputPasswordAdmin" aria-describedby="emailHelp">
				<div id="adminPasswordHelp" class="form-text">Fill this area if you want to login as admin.</div>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>