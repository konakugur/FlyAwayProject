<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administration Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	
	<form action="/FlyAwayProject" style="{display: inline-block;}" method="post">
		<button style="{display: inline-block;}" href="/FlyAwayProject" >HomePage</button>
	</form>
	
	
	
	<form action="/FlyAwayProject/buy" method="post" >
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Date</th>
		      <th scope="col">Time</th>
		      <th scope="col">Source</th>
		      <th scope="col">Destination</th>
		      <th scope="col">Airline</th>
		      <th scope="col">Price</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${flights}" var="flight">
		  		<tr>
			      <th scope="row">${flight.flightId}</th>
			      <td>${flight.date}</td>
			      <td>${flight.time}</td>
			      <td>${flight.source }</td>
			      <td>${flight.destination }</td>
			      <td>${flight.airline }</td>
			      <td>${flight.price} $</td>
			    </tr>
		  	</c:forEach>
		  </tbody>
		</table>
	</form>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>