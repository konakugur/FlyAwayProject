<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Submit Payment</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<style>
.ticket {
	position: relative;
	box-sizing: border-box;
	width: 350px;
	height: 450px;
	margin: 80px 33%;
	padding: 20px;
	border-radius: 10px;
	background: #FBFBFB;
	box-shadow: 2px 2px 15px 0px #AB9B0D; &: before , & : after {
    content : '';
	position: absolute;
	left: 5px;
	height: 6px;
	width: 290px;
}

&
:before {
	top: -5px;
	background: radial-gradient(circle, transparent, transparent 50%, #FBFBFB 50%,
		#FBFBFB 100%) -7px -8px/16px 16px repeat-x,
}

&
:after {
	bottom: -5px;
	background: radial-gradient(circle, transparent, transparent 50%, #FBFBFB 50%,
		#FBFBFB 100%) -7px -2px/16px 16px repeat-x,
}

}
.ticket__content {
	box-sizing: border-box;
	height: 100%;
	width: 100%;
	border: 6px solid #D8D8D8;
}
</style>

</head>
<body>

	<% request.setAttribute("buySuccessfull", true); %>
	
	<form action="/FlyAwayProject/makePayment" method="post" class="container">
		<div class="ticket">
			<div class="ticket__content">
				<p>
					<strong>Details of your flight:</strong>
				</p>
				<p>
					From <strong>${flight.source}</strong> To : <strong>${flight.destination}</strong>
				</p>
				<p>
					At : <strong>${flight.date} on ${flight.time}</strong>
				</p>
				<p>
					Airline : <strong>${flight.airline}</strong>
				</p>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label for="ccnumber">Credit Card Number</label>
							<div class="input-group">
								<input class="form-control" type="text"
									placeholder="0000 0000 0000 0000" maxlength="16">
								<div class="input-group-append">
									<span class="input-group-text"> <i
										class="mdi mdi-credit-card"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-4">
						<label for="ccmonth">Month</label> <select name="cardMonth" class="form-control"
							id="ccmonth">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
						</select>
					</div>
					<div class="form-group col-sm-4">
						<label for="ccyear">Year</label> <select class="form-control"
							id="ccyear">
							<option>22</option>
							<option>23</option>
							<option>24</option>
							<option>25</option>
						</select>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label for="cvv">CVV/CVC</label> <input class="form-control"
								id="cvv" type="text" placeholder="123" maxlength="3">
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary float-right mt-3">Pay
					${flight.price } $</button>
			</div>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>