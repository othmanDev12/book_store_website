<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new User</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">

</head>
<body>
     <jsp:directive.include file="header.jsp" />
     <br>
     
     <div align="center" class="headingpage">
     
        <c:if test="${user != null }">
            <h3>Edit User</h3>
        </c:if>
        
        <c:if test="${user == null }">
            <h3>Create new User</h3>
        </c:if>
        
     </div>
     
      <div class="container mt-2 mb-4">
         <div class="row justify-content-lg-center">
            <div class="col-md-4">
            <c:if test="${user != null }">
                <form method="post" action="update_user" onSubmit="vlidateForm()">
                <input type="hidden" name="userId" value="${user.userId }">
            </c:if>
            <c:if test="${user == null }">
                <form method="post" action="create_user" onSubmit="validateForm()">
            </c:if>
            
                <div class="from-group mb-2">
                   <label for="fullname">Full Name: </label>
                   <input type="text" id="fullname" name="fullname" class="form-control" value="${user.fullName }">
                   <label for="email">Email: </label>
                   <input type="email" id="email" name="email" class="form-control" value="${user.email }">
                   <label for="password">Password: </label>
                   <input type="password" id="password" name="password" class="form-control" value="${user.fullName }">
                </div>
                <div align="center">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="button" class="btn btn-danger" onClick="javascript:history.go(-1);">Cancel</button>
                </div>
            </form>
                
            </div>
         </div>
      </div>
        
     </div>
     <br>
     <div align="center">Bookstore Administration</div>
     
     <jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">
    function validateForm() {
    	var fieldEmail = document.getElementById("email");
    	var fieldFullname = document.getElementById("fullname");
    	var fieldPassword = document.getElementById("password");
    	
    	if(fieldEmail.value.length === 0) {
    		alert("Email are required");
    		fieldEmail.focus();
    		return false;
    	}
    	
    	if(fieldFullname.value.length === 0) {
    		alert("Full Name are required");
    		fieldFullname.focus();
    		return false;
    	}
    	
    	if(fieldPassword.value.length === 0) {
    		alert("Password are required");
    		fieldPassword.focus();
    		return false;
    	}
    	
    	return true;
    }
</script>
</html>