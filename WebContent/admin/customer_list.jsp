<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customers List Management - Online Book Store</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
<body>
     <jsp:directive.include file="header.jsp" />
     
     <div align="center" class="mt-2 mb-2 headingpage">
        <h3>Customers Management</h3>
     </div>
     
     <div align="center">
       <button class="btn btn-warning btn-lg">
          <a href="create_customer">Create Customer</a>
       </button>
     </div>
     
     <div align="center">
         <h4>${message }</h4>
     </div>
     
     <div class="container mt-3 mb-3">
        <div class="row">
            <div class="col">
                <table class="table table-bordered">
                  <thead class="thead-light">
                     <tr>
                       <th>Index</th>
                       <th>ID</th>
                       <th>Email</th>
                       <th>Full Name</th>
                       <th>City</th>
                       <th>Country</th>
                       <th>Registered Date</th>
                       <th>Actions</th>
                     </tr>
                  </thead>
                  
                  <tbody>
                    <c:forEach items="${customers }" var="customer" varStatus="status">
                     <tr>
                     
                        <td>${status.index + 1 }</td>
                        <td>${customer.customerId }</td>
                        <td>${customer.email }</td>
                        <td>${customer.fullName }</td>
                        <td>${customer.city }</td>
                        <td>${customer.country }</td>
                        <td>${customer.registerDate }</td>
                        <td>
                           <button class="btn btn-warning" type="button">
                              <a href="edit_customer?id=${customer.customerId}">Edit</a>
                           </button>
                           <button class="btn btn-danger" type="button">
                              <a href="javascript:confirmDelete(${customer.customerId })">Delete</a>
                           </button>
                        </td>
                     </tr>
                     
                    </c:forEach>
                  </tbody>
                </table>
            </div>
        </div>
     </div>
     
     <jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
    function confirmDelete(id) {
    	if(confirm("are you sure the you want to delete this customer with id " + id)) {
    		window.location = "delete_customer?id=" + id;
    	}
    }
</script>
</html>