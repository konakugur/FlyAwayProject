<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Your Flight</title>
	
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">


<style>
		.error{
			color:red;
		}
	</style>
</head>
<body>

	<%
		if (session.getAttribute("sessionFactory") == null) {
			PrintWriter writer = response.getWriter();
			writer.println("<h1>You must be logged in first!");
			response.sendRedirect("index.jsp");
		}
	%>
	
	<div>
	<%
		String errMsg = (String) request.getAttribute("searchErrorMessage");
		if(errMsg != null){
		%>
		<span class='error'><%= errMsg %></span>
			<%} %>
	</div>

	<form action="/FlyAwayProject/search" method=POST>
	
	
		<div class="card shadow mb-5 bg-white rounded">
	    <!--Card-Body-->
	    <div class="card-body">
	        <!--Card-Title-->
	        <p class="card-title text-center shadow mb-5 rounded">Travel Booking Form</p>
	        <div class="icons text-center">
	            <i class="fa fa-plane fa-2x" aria-hidden="true"></i>
	            <i class="fa fa-taxi fa-2x" aria-hidden="true"></i>
	            <i class="fa fa-train fa-2x" aria-hidden="true"></i> </div>
	        <hr>
	        <p class="searchText"><strong>Search For Cheap Flights - Please keep in mind that tickets are sold only 3 days before the flight. </strong></p>
	        <div class="row">
	            <div class="col-sm-6" > <select name="cityFrom" class="custom-select custom-select-lg mr-sm-2" id="select">
	                    <option value="" disabled="" selected="">From City/Airport</option>
	                    <option value="Istanbul">Istanbul</option>
	                    <option value="Paris">Paris</option>
	                    <option value="London">London</option>
	                </select> </div>
	            <div class="col-sm-6"> <select name="cityTo" class="custom-select custom-select-lg mr-sm-2" id="select2">
	                    <option value="" disabled="" selected="">To City/Airport</option>
	                    <option value="Istanbul">Istanbul</option>
	                    <option value="Paris">Paris</option>
	                    <option value="London">London</option>
	                </select> </div>
	        </div>
	        <hr>
	        <!--Fourth Row-->
        
        
			  <div class="row">
			    <div class='col-sm-4'>
			    	<p class="searchText">Time To Travel</p>
			      	<div class='input-group date' id='datetimepicker1'>
			          <input name="flightDate" type='text' class="form-control" />
			          <span class="input-group-addon">
			            <span class="glyphicon glyphicon-calendar"></span>
			          </span>
			        </div>
			    </div>
			  </div>
			<hr>
	        <!--Fifth Row-->
	        <div class="row">
	            <div class="col-sm-4"> <select class="custom-select custom-select-lg mr-sm-2" id="select3" name="numberOfPeople" >
	                    <option value="" disabled="" selected="">Number of People</option>
	                    <option value="1">1</option>
                    	<option value="2">2</option>
                    	<option value="3">3</option>
                	</select> </div>
        		</div> <button type="submit" class="btn btn-primary float-right mt-5">Find Flights</button>
    		</div>
		</div>
	</form>	
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datetimepicker1').datetimepicker({
        	minDate: moment(),
        	format: 'MM/DD/YYYY'
        });
    });
</script>
</body>
</html>
