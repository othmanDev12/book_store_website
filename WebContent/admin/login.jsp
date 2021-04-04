<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Administration</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
   <div align="center" class="mt-5">
     <h3>Book Store Administration</h3>
   </div>
   
   <div align="center">
      <h5>Admin Login</h5>
   </div>
   
   <div align="center">
    <h4><i>${message }</i></h4>
   </div>
   
   
   
   <div class="container">
      <div class="row justify-content-md-center mb-2">
         <div class="col-md-4">
           <form action="login" method="post" onSubmit="validateForms()">
               <div class="form-group">
             <label for="email">Email:</label>
             <input class="form-control" id="email" name="email" type="text">
             <label for="password">Password:</label>
             <input class="form-control" id="password" name="password" type="password">
           </div>
           <div align="center">
             <button class="btn btn-primary">Login</button>
           </div>
           </form>
         </div>
      </div>
   </div>

</body>
<script type="text/javascript">
   function validateForms() {
	   var email = document.getElementById("email");
	   var password = document.getElementById("password");
	   
	   if(email.value.length === 0) {
		   alert("the email was required")
		   email.focus();
		   return false;
	   }
	   
	   if(password.value.length === 0) {
		   alert("the password was required")
		   password.focus();
		   return false;
	   }
	   
	   return true;
   }
</script>
</html>