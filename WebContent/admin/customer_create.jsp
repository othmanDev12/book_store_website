<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Customer - Online Book store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />


	<c:if test="${customer == null }">
		<div align="center" class="my-2 headingpage">
			<h3>Create Customer</h3>
		</div>
	</c:if>

	<c:if test="${customer != null }">
		<div align="center" class="my-2 headingpage">
			<h3>Edit Customer</h3>
		</div>
	</c:if>



	<div class="container mb-3">
		<c:if test="${customer == null }">
			<form action="create_customer" method="post"
				onSubmit="validateForm()">
		</c:if>

		<c:if test="${customer != null }">
			<form action="update_customer" method="post"
				onSubmit="validateForm()">
				<input type="hidden" class="form-control" name="id" id="id" value="${customer.customerId }">
		</c:if>

		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="email">E-mail Address:</label> <input
						class="form-control" id="email" type="text" name="email"
						value="${customer.email }">
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
					<label for="password">Password:</label> <input class="form-control"
						id="password" type="password" name="password"
						value="${customer.password }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="password">Confirm Password:</label> <input
						class="form-control" id="confirmpassword" type="password"
						name="password" value="${customer.password }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="phone">Phone Number:</label> <input
						class="form-control" id="phone" type="text" name="phone"
						value="${customer.phone }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="address">Address:</label> <input class="form-control"
						id="address" type="text" name="address"
						value="${customer.address }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="city">City:</label> <input class="form-control"
						id="city" type="text" name="city" value="${customer.city }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">

			<div class="col-md-4">
				<div class="form-group">
					<label for="zipcode">Zip Code:</label> <input class="form-control"
						id="zipcode" type="text" name="zipcode"
						value="${customer.zipcode }">
				</div>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="form-group">
					<label for="country">Country:</label> <input class="form-control"
						id="country" type="text" name="country"
						value="${customer.country }">
				</div>
			</div>
		</div>
		<div align="center">
			<button class="btn btn-primary" type="submit">Save</button>
			<button class="btn btn-danger" type="button" onClick="javascript:history.go(-1);">Cancel</button>
		</div>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateForm() {
		var email = document.getElementById("email");
		var fullname = document.getElementById("fullname");
		var password = document.getElementById("password");
		var confirmpassword = document.getElementById("confirmpassword");
		var phone = document.getElementById("phone");
		var address = document.getElementById("address");
		var city = document.getElementById("city");
		var zipcode = document.getElementById("zipcode");
		var country = document.getElementById("country");

		if (email.value.length === 0) {
			alert("the email was required");
			email.focus();
			return false;
		}

		if (fullname.value.length === 0) {
			alert("the fullname was required");
			fullname.focus();
			return false;
		}

		if (password.value.length === 0) {
			alert("the password was required");
			password.focus();
			return false;
		}

		if (confirmpassword.value.length === 0) {
			alert("the confirm password was required");
			confirmpassword.focus();
			return false;
		}

		if (confirmpassword.value !== password.value) {
			alert("the confirm password was incorrect check your password");
			return true;
		}

		if (phone.value.length === 0) {
			alert("the phone number was required");
			phone.focus();
			return false;
		}

		if (address.value.length === 0) {
			alert("the address was required");
			address.focus();
			return false;
		}

		if (city.value.length === 0) {
			alert("the email was required");
			email.focus();
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

		return true;

	}
</script>

</html>