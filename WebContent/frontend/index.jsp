<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Book Store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>

	<jsp:directive.include file="header.jsp" />


	
	<div align="center">
		<h3>This is the main content:</h3>
		<h3>New Books:</h3>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="row">
						<c:forEach items="${newBooks }" var="book">
							<div class="col-md-3">
								<div class="card"
									style="height: 500px; border-radius: 10px; box-shadow: 0 6px 2px -2px grey;">
									<a href="book_detail?id=${book.bookId }"><img
										src="data:image/jpg;base64,${book.base64Image } "
										style="height: 250px; width: 253px;"></a>
									<div class="card-body">
										<div>
											<a href="book_detail?id=${book.bookId }">${book.title }</a>
										</div>
										<div>
										   <c:forTokens items="${book.ratingStars }" delims="," var="star">
										       <c:if test="${star == 'on' }">
										          <img src="images/rating_on.png">
										       </c:if>
										       
										        <c:if test="${star == 'half' }">
										          <img src="images/rating_half.png">
										        </c:if>
										        
										        <c:if test="${star == 'off' }">
										           <img src="images/rating_off.png">
										        </c:if>
										   </c:forTokens>
										</div>
										<div><i>by ${book.author }</i></div>
										<div><b>$ ${book.price }</b></div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<h2>Best-Selling Books:</h2>
		<h2>Most-Favored Books:</h2>
	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>