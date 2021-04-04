
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<div align="center">
	<img alt="image" src="images/logo.png">
</div>

<div align="center">

	<form action="search" method="get">
		<div class="container">
			<div class="row" style="justify-content: center;">
				<div class="col-md-5">
					<input type="text" name="keyword" class="form-control ">
				</div>
				<button type="submit" class="btn btn-primary">Search</button>
			</div>

		</div>
		<div>
			<c:if test="${loggedcustomer == null }">
				<a href="login">Sign In</a> |
                   <a href="register">Register</a> |
              </c:if>

			<c:if test="${loggedcustomer != null }">
				<a href="view_profile">Welcome,${loggedcustomer.fullName }</a> |
                   <a href="view_order">My Orders</a> |
                   <a href="logout">Logout</a> |
              </c:if>
              <a href="view_cart">Cart</a>
           
		</div>
	</form>
	<div align="center" class="my-2">
		<c:forEach var="category" items="${categories }">
			<b><a href="view_book?id=${category.categoryId }">${category.name }</a>
				| </b>
		</c:forEach>
	</div>


</div>