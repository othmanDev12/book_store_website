<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>books by ${category.name } - Online Books Store</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

     <div align="center"  class="my-3" style="color: gray;">
         <h3>${category.name }</h3>
     </div>

	<div class="container my-5">
		<div class="row">
			<div class="col">
				<div class="row">
					<c:forEach items="${listBooks }" var="book">
						<div class="col-md-3">
							<div class="card mb-3" style="height: 500px; border-radius: 10px; box-shadow: 0 6px 2px -2px grey;">
								<a href="book_detail?id=${book.bookId }"><img src="data:image/jpg;base64,${book.base64Image } " style="height: 250px; width: 253px;"></a>
								<div class="card-body">
									<div><a href="book_detail?id=${book.bookId }">${book.title }</a></div>
									<div>
									    <c:forTokens items="${book.ratingStars}" delims="," var="star">
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
									<div><b>$${book.price }</b></div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>




	<jsp:directive.include file="footer.jsp" />
</body>
</html>