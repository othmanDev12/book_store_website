<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center" class=" mb-3">
		<h4 style="color: darkblue;">Welcome , ${loggedcustomer.fullName }</h4>
	</div>

	<div class="container" style="margin-left: 400px">
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>E-mail Address: </b> ${loggedcustomer.email}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>Full Name: </b> ${loggedcustomer.fullName}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>Phone Number: </b> ${loggedcustomer.phone}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>Address:: </b> ${loggedcustomer.address}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>City: </b> ${loggedcustomer.city }
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>Zip Code: </b> ${loggedcustomer.zipcode}
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div>
					<b>Country: </b> ${loggedcustomer.country}
				</div>
			</div>
		</div>
	</div>

	<div align="center" class="mb-5">
		<button class="btn btn-warning">
			<a href="edit_profile">Edit Profile</a>
		</button>
	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>