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
		<form action="submit_review" method="post" onSubmit="validateForm()">

			<div class="row">
				<div class="col">
					<div class="row">
						<div class="col-md-10">
							<h4>your already wrote a review for this book</h4>
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
							<div id="rateYo" class="mt-5 mb-3"></div>
							<input type="hidden" class="form-control" name="rating"
								id="rating"> <input type="hidden" class="form-control"
								name="id" id="id" value="${book.bookId }"> <input type="text"
								class="form-control mb-2" name="headline" id="headline" value="${review.headline }">
							<textarea  class="form-control" name="comment"
								id="comment" cols="70" rows="10">${review.comment }</textarea>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:directive.include file="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("#rateYo").rateYo({
				starWidth : "40px",
				fullStar : true,
				rating: ${review.rating},
				readOnly: true,
			});
		});
	</script>
</body>
</html>