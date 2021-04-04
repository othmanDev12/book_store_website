<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Form - Online Book Store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/jquery.rateyo.min.css">
<!-- Latest compiled and minified JavaScript -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.rateyo.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="container mt-3 mb-5">
		<div class="row">
			<div class="col">
				<div class="row">
					<div class="col-md-10">
						<h4>your Review</h4>
					</div>
					<div class="col-md-2">
						<div>${loggedcustomer.fullName }</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-4">
						<div class="text-center">
							<b>${book.title }</b>
						</div>

						<div align="center" class="mt-2">
							<img style="height: 350px;"
								src="data:image/jpg;base64,${book.base64Image }">
						</div>
					</div>

					<div class="col-md-8 mb-2">
						<div class="alert alert-primary" role="alert">the review was submited by ${loggedcustomer.fullName } Thank you !!!</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:directive.include file="footer.jsp" />
</body>
</html>