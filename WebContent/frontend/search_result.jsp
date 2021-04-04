<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>search for ${keyword } - Book Store Online</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h3>Search Results for ${keyword }</h3>
	</div>

	<div align="center">
		<c:if test="${fn:length(resualt) == 0 }">
			<h3>No Result For "${keyword }"</h3>
		</c:if>
	</div>

	<c:if test="${fn:length(resualt) > 0 }">
		<div class="container">
			<div class="row">
				<div class="col">
					<c:forEach items="${resualt}" var="book">
						<div class="card mb-3">
							<div class="row">
								<div class="col-md-2">
									<a href="book_detail?id=${book.bookId }"><img
										src="data:image/jpg;base64,${book.base64Image } "
										style="height: 164px;"></a>
								</div>
								<div class="col-md-7">
									<div class="mt-4">
										<a href="book_detail?=id${book.bookId }">${book.title }</a>
									</div>
									<div>rating *****</div>
									<div><i>${book.author }</i></div>
									<div>${fn:substring(book.description, 0, 100) }...</div>
								</div>
								<div class="col-md-3">
								   <div class="mt-4">
								      <div><b>$${book.price }</b></div>
								   </div>
								   <div ><button class="btn btn-primary">Add Cart</button></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:if>

    <jsp:directive.include file="footer.jsp" />

</body>
</html>