<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3>Customer Login</h3>
	</div>

	<div align="center">
		<h3>${message }</h3>
	</div>

	<div class="container mb-3">
		<form action="login" method="post" onSubmit="validateForm()">
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="email">Email:</label> <input class="form-control"
							type="text" id="email" name="email">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="password">Password:</label> <input
							class="form-control" type="password" id="password"
							name="password">
					</div>
				</div>
			</div>

			<div align="center">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form>
	</div>


	<jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
	function validateForm() {
		var email = document.getElementById("email");
		var password = document.getElementById("password");

		if (email.value.length === 0) {
			alert("the email was required");
			email.focus();
			return false;
		}
		if (password.value.length === 0) {
			alert("the password was required");
			password.focus();
			return false;
		}

		return true;
	}
</script>
</html>