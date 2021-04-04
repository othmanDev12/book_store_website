<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center" class="mb-2">
		<h3 style="color: darkblue;">Edit Profile</h3>
	</div>

	<div class="container mb-5">
		<form action="update_profile" method="post" onSubmit="validateForm()">
			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label>E-mail Address:</label>
						<div>
							<b>${loggedcustomer.email } (Cannot be change)</b>
						</div>
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="fullname">Full Name:</label> <input
							class="form-control" name="fullname" id="fullname"
							value="${loggedcustomer.fullName }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="phone">Phone Number:</label> <input
							class="form-control" name="phone" id="phone"
							value="${loggedcustomer.phone }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="address">Address:</label> <input class="form-control"
							name="address" id="address" value="${loggedcustomer.address }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="city">City:</label> <input class="form-control"
							name="city" id="city" value="${loggedcustomer.city }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="zipcode">Zip Code:</label> <input class="form-control"
							name="zipcode" id="zipcode" value="${loggedcustomer.zipcode }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="fullname">Country:</label> <input class="form-control"
							name="country" id="country" value="${loggedcustomer.country }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-6">
					<div>(leave password fields blank if you don't want to change
						password)</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="password">Password:</label> <input
							class="form-control" name="password" id="password">
					</div>
				</div>
			</div>


			<div class="row justify-content-center">
				<div class="col-md-5">
					<div class="form-group">
						<label for="confirmpassword">Full Name:</label> <input
							class="form-control" name="password" id="confirmpassword">
					</div>
				</div>
			</div>
			
			<div align="center">
			   <button class="btn btn-primary" type="submit">Update</button>
			   <button class="btn btn-danger" type="button" onClick="javascript:history.go(-1);">Cancel</button>
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
	   
	   if(fullname.value.length === 0) {
		   alert("the full name was required");
		   fullname.focus();
		   return false;
	   }
	   
	   if(phone.value.length === 0) {
		   alert("the phone was required");
		   phone.focus();
		   retrn false;
	   }
	   
	   if(address.value.length === 0) {
		   alert("the address was required");
		   address.focus();
		   return false;
	   }
	   
	   if(city.value.length === 0) {
		   alert("the city was required");
		   city.focus();
		   return false;
	   }
	   
	   if(zipcode.value.length === 0) {
		   alert("the zip code was required");
		   zipcode.focus();
		   return false;
	   }
	   
	   if(country.value.length === 0) {
		   alert("the country was required");
		   country.focus();
		   return false;
	   }
	   
	   if(password.value !== confirmPassword.value) {
		   alert("the password is incorrect please check your password");
		   return false;
	   }
	   
	   return true;
   }
</script>
</html>