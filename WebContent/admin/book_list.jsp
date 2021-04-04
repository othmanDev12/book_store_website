<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Managment</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <jsp:directive.include file="header.jsp" />
    
    <div align="center" class="headingpage mt-2 mb-2">
        <h3>Books Management</h3>
    </div>
    
    
    <div align="center" >
       <button class="btn btn-warning btn-lg mt-2"><a href="create_book">Create New Book</a></button>
    </div>
    
    <div align="center">
         <h4>${message }</h4>
    </div>
    
    <div class="container mt-3 mb-3">
       <div class="row">
           <div class="col-md-12">
                  <table class="table table-bordered">
                      <thead class="thead-light">
                           <tr>
                               <th>Index</th>
                               <th>ID</th>
                               <th>Image</th>
                               <th>Title</th>
                               <th>Author</th>
                               <th>Category</th>
                               <th>Price</th>
                               <th>Last Update</th>
                               <th>Actions</th>
                           </tr>
                      </thead>
                      <c:forEach var="book" items="${bookList }"  varStatus="status">
                      <tbody>
                           <tr>
                              <td>${status.index + 1 }</td>
                              <td>${book.bookId }</td>
                              <td><img src="data:image/jpg;base64,${book.base64Image }" width="84" height="110"></td>
                              <td >${book.title }</td>
                              <td>${book.author }</td>
                              <td>${book.category.name }</td>
                              <td>${book.price }$</td>
                              <td>${book.lastUpdate }</td>
                              <td>
                                 <button class="btn btn-warning mb-1" type="button"><a href="edit_book?id=${book.bookId }">Edit</a></button>
                                 <button class="btn btn-danger" type="button" ><a href="javascript:confirmDelete(${book.bookId})">Delete</a></button>
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
       function confirmDelete(bookId) {
    	   if(confirm("are you sure that you want to delet this book with ID " + bookId)) {
    		   window.location = "delete_book?id=" + bookId;
    	   }
       }
  </script>
</html>