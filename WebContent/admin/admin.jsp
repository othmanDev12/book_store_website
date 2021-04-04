<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<title>Evergreen Books - Administration</title>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
   <link rel="stylesheet" href="../css/style.css">
</head>
<body>
     <jsp:directive.include file="header.jsp" />
     <br>
     
     <div align="center" class="headingpage">
       <h2>Administration Dashboard</h2>
     </div>
     
     <br>
     
     <div align="center">
         <h1>Quick Actions:</h1>
         <a href="newbook">New Book</a>&nbsp;
         <a href="newuser">New User</a>&nbsp;
         <a href="newcategory">New Category</a>&nbsp;
         <a href="newcustomer">New Costumer</a>
     </div>
     <br>
     
     <div align="center" class="headingpage">
         <h2>Recent Sales:</h2>
     </div>
     <br>
     
     <div align="center" class="headingpage">
        <h2>Recent Reviews:</h2>
     </div>
     <br>
     
     <div align="center" class="headingpage">
        <h2>Statics: </h2>
     </div>
     <br>
     <div align="center">Bookstore Administration</div>
     
     <jsp:directive.include file="footer.jsp" />
</body>
</html>