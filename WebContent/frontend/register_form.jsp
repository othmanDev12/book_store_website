<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Customer - Online Book store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />


	<div align="center" class="my-2 headingpage">
		<h3>Register as Customer</h3>
	</div>




	<div class="container mb-3">

		<form action="register_customer" method="post"
			onSubmit="validateForm()">
			<input type="hidden" class="form-control" name="id" id="id">

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="email">E-mail Address:</label> <input
							class="form-control" id="email" type="text" name="email">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="fullname">Full Name:</label> <input
							class="form-control" id="fullname" type="text" name="fullname"
							value="${customer.fullName }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="password">Password:</label> <input
							class="form-control" id="password" type="password"
							name="password">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="password">Confirm Password:</label> <input
							class="form-control" id="confirmpassword" type="password"
							name="password">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="phone">Phone Number:</label> <input
							class="form-control" id="phone" type="text" name="phone">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="address">Address:</label> <input class="form-control"
							id="address" type="text" name="address">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="city">City:</label> <input class="form-control"
							id="city" type="text" name="city">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">

				<div class="col-md-4">
					<div class="form-group">
						<label for="zipcode">Zip Code:</label> <input class="form-control"
							id="zipcode" type="text" name="zipcode">
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="country">Country:</label> <input class="form-control"
							id="country" type="text" name="country">
					</div>
				</div>
			</div>
			<div align="center">
				<button class="btn btn-primary" type="submit">Save</button>
				<button class="btn btn-danger" type="button"
					onClick="javascript:history.go(-1);">Cancel</button>
			</div>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateForm() {
		var fullname = document.getElementById("fullname");
		var phone = document.getElementById("phone");
		var address = document.getElementById("address");
		var city = document.getElementById("city");
		var zipcode = document.getElementById("zipcode");
		var country = document.getElementById("country");
		var password = document.getElementById("password");
		var confirmPassword = document.getElementById("confirmpassword");

		if (fullname.value.length === 0) {
			alert("the full name was required");
			fullname.focus();
			return false;
		}

		if (phone.value.length === 0) {
			alert("the phone was required");
			phone.focus();
			return false;
		}

		if (address.value.length === 0) {
			alert("the address was required");
			address.focus();
			return false;
		}

		if (city.value.length === 0) {
			alert("the city was required");
			city.focus();
			return false;
		}

		if (zipcode.value.length === 0) {
			alert("the zip code was required");
			zipcode.focus();
			return false;
		}

		if (country.value.length === 0) {
			alert("the country was required");
			country.focus();
			return false;
		}

		if (password.value !== confirmPassword.value) {
			alert("the password is incorrect please check your password");
			return false;
		}

		return true;
	}
</script>

</html>