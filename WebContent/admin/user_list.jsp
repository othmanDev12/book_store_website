<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Users - Evergreen Book Store</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
     <jsp:directive.include file="header.jsp" />
     
     <div align="center" class="headingpage mt-2">
       <h3>Users Management</h3>
     </div>
    <div align="center" >
       <button type="button" class="btn btn-warning mt-2 btn-lg" ><a href="create_user">Create new User</a></button>
    </div>
    
    <!-- Display a message for successful creation -->
        <div align="center">
         <h4>${message}</h4>
        </div>
    
      <div class="container mt-3">
          <div class="row justify-content-lg-center">
              <div class="col-md-8">
                     <div align="center">
        <table class="table table-bordered">
          <thead class="thead-light" >
            <tr>
             <th>Index</th>
             <th>ID</th>
             <th>Email</th>
             <th>Full Name</th>
             <th>Actions</th>
           </tr>
          </thead>
           
           <!-- fetch list users in the SERVLET -->
           <tbody>
               <c:forEach items="${usersList}" var="user" varStatus="status">
               <tr>
                 <td>${status.index + 1}</td>
                 <td>${user.userId}</td>
                 <td>${user.email}</td>
                 <td>${user.fullName}</td>
                 <td>
                   <button type="button" class="btn btn-warning"><a href="edit_user?id=${user.userId}">Edit</a></button> &nbsp;
                   <button type="button" class="btn btn-danger"><a href="javascript:confirmDelete(${user.userId })">Delete</a></button>
                 </td>
               </tr>
           </c:forEach>
           </tbody>
        </table>
    </div> 
              </div>
          </div>
      </div>  

     <div align="center">Bookstore Administration</div>
     <br>
     <jsp:directive.include file="footer.jsp" />
</body>
 <script type="text/javascript">
      function confirmDelete(userId) {
    	  if(confirm("are you sure that you want to delete this user with ID " + userId)) {
    		  window.location = "delete_user?id=" + userId;
    	  }
      }
 </script>
</html>