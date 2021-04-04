<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Category Management - Book Store Online</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>

    <jsp:directive.include file="header.jsp" />
    
    <div align="center" class="headingpage  mt-2">
       <h3>Category Management</h3>
    </div>
    
    
    <div align="center">
       <button type="button" class="btn btn-warning btn-lg mt-2"><a href="create_category">Create new Category</a></button>
    </div>
    
    <div align="center">
       <h4><i>${message }</i></h4>
    </div>
    
    <div class="container mt-3">
        <div class="row justify-content-lg-center">
           <div class="col-md-8">
                <table class="table table-bordered">
          <thead class="thead-light" >
            <tr>
             <th>Index</th>
             <th>ID</th>
             <th>Name</th>
             <th>Actions</th>
           </tr>
          </thead>
           
           <!-- fetch list users in the SERVLET -->
           <tbody>
               <c:forEach items="${listCategory}" var="category" varStatus="status">
               <tr>
                 <td>${status.index + 1}</td>
                 <td>${category.categoryId}</td>
                 <td>${category.name}</td>
                 <td>
                   <button type="button" class="btn btn-warning"><a href="edit_category?id=${category.categoryId}">Edit</a></button> &nbsp;
                   <button type="button" class="btn btn-danger"><a href="javascript:deleteConfirm(${category.categoryId })">Delete</a></button>
                 </td>
               </tr>
           </c:forEach>
           </tbody>
        </table>
           </div>
        </div>
    </div>
    
    <div align="center">
         <p>Bookstore Administration</p>
    </div>
    
    <jsp:directive.include file="footer.jsp" />

</body>
    <script type="text/javascript">
        function deleteConfirm(id) {
        	if(confirm("are you sure that you want to delete the category with the ID " + id )) {
        		window.location = "delete_category?id=" + id;
        	}
        }
    </script>
</html>