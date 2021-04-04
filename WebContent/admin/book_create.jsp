<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books Management - Create New Book</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<c:if test="${book != null }">
	  <div align="center" class="headingpage mt-2">
		<h3>Edit Book</h3>
	  </div>
	</c:if>
	
    <c:if test="${book == null }">
       <div align="center" class="headingpage mt-2">
		 <h3>Create New Book</h3>
	   </div>
    </c:if>


	<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-4">
				<c:if test="${book == null }">
				     <form action="create_book" method="post" onSubmit="validateForm()" enctype='multipart/form-data' >
				</c:if>
				
				<c:if test="${book != null }">
				     <form action="update_book" method="post" onSubmit="validateForm()" enctype='multipart/form-data' >
				     <input type="hidden" class="form-control" value="${book.bookId }" name="id">
				</c:if>


					<div class="form-group">
						<label class="category">Category</label> 
						<select
							class="form-control" id="category" name="category">
                              <c:if test="${book == null }">
                                <c:forEach var="category" items="${categoryList }">
							       <option value="${category.categoryId }">${category.name }</option>
							    </c:forEach>
                              </c:if>
                              
                              <c:if test="${book != null }">
                                <c:forEach var="category" items="${categories }">
							       <option value="${category.categoryId }">${category.name }</option>
							    </c:forEach>
                              </c:if>
						</select>
					</div>
					

                      <input type="hidden" class="form-control" name="last" id="last">

					<div class="form-group">
						<label for="title">Title</label> <input type="text"
							class="form-control" id="title" name="title" value="${book.title }">
					</div>

					<div class="form-group">
						<label for="author">Author</label> <input type="text"
							class="form-control" id="author" name="author" value="${book.author }">
					</div>

					<div class="form-group">
						<label for="isbn">ISBN</label> <input type="text"
							class="form-control" id="isbn" name="isbn" value="${book.isbn }">
					</div>

					<div class="form-group">
						<label for="publish">Publish Date</label> <input type="text"
							class="form-control" id="publish" name="publish" value="${book.publishDate }">
					</div>
                     
                     
                     <div class="form-group">
						<label for="image">Image</label> <input type="file"
							class="form-control" id="image" name="image" value="${book.image }">
					</div>
					

					<div class="form-group">
						<label for="price">Price</label> <input type="text"
							class="form-control" id="price" name="price" value="${book.price }">
					</div>

					<div class="form-group">
						<label for="description">Description</label>
						<textarea rows="10" cols="30" class="form-control"
							id="description" name="description">${book.description }</textarea>
					</div>

					<div align="center">
						<button type="submit" class="btn btn-primary">Save</button>
						<button type="button" class="btn btn-danger"
							onclick="javascript:history.go(-1);">cancel</button>
					</div>

				</form>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
  $( function() {
    $( "#publish" ).datepicker();
  } );
  
  function validateForm() {
	  var category = document.getElementById("category");
	  var title = document.getElementById("title");
	  var author = document.getElementById("author");
	  var isbn = document.getElementById("isbn");
	  var publish = document.getElementById("publish");
	  var image = document.getElementById("image");
	  var price = document.getElementById("price");
	  var description = document.getElementById("description");
	  
	  
	  if(category.value.length === 0) {
		  alert("the category was required");
		  category.focus();
		  return false;
	  }
	  
	  if(title.value.length === 0) {
		  alert("the title was required");
		  title.focus();
		  return false;
	  }
	  
	  if(author.value.length === 0) {
		  alert("the author was required");
		  author.focus();
		  return false;
	  }
	  
	  if(isbn.value.length === 0) {
		  alert("the isbn was required");
		  isbn.focus();
		  return false;
	  }
	  
	  if(publish.value.length === 0) {
		  alert("the publish was required");
		  publish.focus();
		  return false;
	  }
	  
	  if(image.value.length === 0) {
		  alert("the image was required");
		  image.focus();
		  return false;
	  }
	  
	  if(price.value.length === 0) {
		  alert("the price was required");
		  price.focus();
		  return false;
	  }
	  
	  if(description.value.length === 0) {
		  alert("the description was required");
		  description.focus();
		  return false;
	  }
	  
	  return true;
	  
  }
</script>

</html>