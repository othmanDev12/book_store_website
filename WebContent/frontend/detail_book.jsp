<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>${book.title }- Book store Online</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="container mt-5 mb-5">
		<div class="row">
			<div class="col">
				<div class="row mb-3">
					<div class="col-md-3">
						<h5 class="text-center">${book.title }</h5>
						<div class="text-center"><i>by ${book.author }</i></div>
						<img src="data:image/jpg;base64,${book.base64Image } "
							style="height: 300px; width: 240px;">
					</div>
					
					<div class="col-md-6" style="margin-top: 68px;">
					   <div style="margin-bottom: 42px;" >
					      <c:forTokens items="${book.ratingStars }" delims="," var="star">
					          <c:if test="${star == 'on' }">
					             <img src="images/rating_on.png">
					          </c:if>
					          
					          <c:if test="${star == 'off' }">
					             <img src="images/rating_off.png">
					          </c:if>
					          
					          <c:if test="${star == 'half' }">
					              <img src="images/rating_half.png">
					          </c:if>
					      </c:forTokens>
					   </div>
					   <div class="card">
					     <div class="card-body">
					        <div class="mb-2">${book.description }</div>
					     </div>
					   </div>
					</div>
					
					<div class="col-md-3" style="margin-top: 68px;">
					   <div class="mb-3"><b>$${book.price }</b></div>
					   <div><button type="submit" class="btn btn-primary">Add to Cart</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row mb-2">
		    <div class="col-md-6">
		        <h4>Customer Reviews</h4>
		    </div>
		    
		    <div class="col-md-6">
		        <button type="button" class="btn btn-primary" onClick="javascript:reviewForm()">Write a customer review</button>
		    </div>
		</div>
		
		<div class="row justify-content-center">
		   <div class="col-md-7">
		      <c:forEach items="${book.reviews }" var="review">
		         <div class="card mb-2">
		             <div class="card-body" style="border-radius: 10px;">
		                <div class="row">
		                    <div class="col-md-3">
		                       <c:forTokens items="${review.star }" delims="," var="star">
		                          <c:if test="${star == 'on' }">
		                             <img alt="" src="images/rating_on.png">
		                          </c:if>
		                          
		                          <c:if test="${star == 'off' }">
		                             <img alt="" src="imgaes/rating_off.png">
		                          </c:if>
		                       </c:forTokens>
		                    </div>
		                    <div class="col-md-9">
		                        <h5>${review.headline }</h5>
		                    </div>
		                </div>
		                <div class="row">
		                  <div class="col">
		                      <div>
		                         by ${review.customer.fullName } on ${review.reviewTime }
		                      </div>
		                  </div>
		                </div>
		                
		                <div class="row">
		                   <div class="col">
		                     <div class="text-center">
		                        <i>"${review.comment }"</i>
		                     </div>
		                   </div>
		                </div>
		             </div>
		         </div>
		      </c:forEach>
		   </div>
		</div>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
   function reviewForm() {
	   window.location = "write_review?book_id=" + ${book.bookId}
   }
</script>
</html>