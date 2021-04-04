<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Management Review - Evergreen Book Store</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <jsp:directive.include file="header.jsp" />
    
    <div align="center" class="my-2">
       <h3 class="headingpage">Review Management</h3>
    </div>
    
    <div class="container mt-3 mb-5">
       <div class="row">
          <div class="col-md-12">
              <table class="table table-bordered">
                <thead class="thead-light">
                   <tr>
                     <th>Index</th>
                     <th>Id</th>
                     <th>Book</th>
                     <th>Rating</th>
                     <th>Headline</th>
                     <th>Customer</th>
                     <th>Review On</th>
                     <th>Actions</th>
                   </tr>
                </thead>
                <c:forEach items="${listReviews }" var="review" varStatus="status">
                   <tbody>
                      <tr>
                        <td>${status.index + 1 }</td>
                        <td>${review.reviewId }</td>
                        <td>${review.book.title }</td>
                        <td>${review.rating }</td>
                        <td>${review.headline }</td>
                        <td>${review.customer.fullName }</td>
                        <td>${review.reviewTime }</td>
                        <td>
                           <button class="btn btn-warning"><a href="edit_review?id=${review.reviewId}">Edit</a></button>
                           <button class="btn btn-danger"><a href="javascript:confirmDelete(${review.reviewId })">Delete</a></button>
                        </td>
                      </tr>
                   </tbody>
                </c:forEach>
              </table>
          </div>
       </div>
    </div>
    
    <jsp:directive.include file="footer.jsp" />

</body>
<script type="text/javascript">
    function confirmDelete(reviewId) {
    	if(confirm("are you sure that you want to delete this review with ID: " + reviewId)){
    		window.location = "delete_review?id=" + reviewId;
    	}
    }
</script>
</html>