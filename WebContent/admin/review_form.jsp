<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center" class="my-2">
		<h3 class="headingpage">Edit Page</h3>
	</div>

	<div class="container mb-5 mt-3">

		<form action="update_review" method="post" onSubmit="validateForm()">
		    <input type="hidden" class="form-control" name="id" value="${review.reviewId }">
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="mb-2">
						Book: <b>${review.book.title }</b>
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="mb-2">
						Rating: <b>${review.rating }</b>
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="mb-2">
						Customer: <b>${review.customer.fullName }</b>
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="headline">Headline:</label> <input
							class="form-control" id="headline" name="headline" value="${review.headline }">
					</div>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="form-group">
						<label for="comment">Comment:</label>
						<textarea class="form-control" id="comment" name="comment" >${review.comment }</textarea>
					</div>
				</div>
			</div>
			
			<div align="center">
			   <button type="submit" class="btn btn-primary">Save</button>
			   <button type="button" class="btn btn-danger" onClick="javascript:history.go(-1);">Cancel</button>
			</div>
		</form>
		
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
    function validateForm()  {
    	var headline = document.getElementById("headline");
    	var comment = document.getElementById("comment");
    	
    	if(headline.value.length === 0) {
    		alert("the headline was required");
    		headline.focus();
    		return false;
    	}
    	
    	if(comment.value.length === 0)  {
    		alert("the comment was required");
    		comment.focus();
    		return false;
    	}
    	return true;
    }
</script>
</html>