<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

    <jsp:directive.include file="header.jsp" />
       
       
       <c:if test="${category != null }">
         <div align="center" class="mt-2">
             <h3>Edit Category</h3>
         </div>
       </c:if>
       
       
       <c:if test="${category == null }">
         <div align="center" class="mt-2">
             <h3>Create New Category</h3>
         </div>
       </c:if>
       
       
       
      <div class="container mt-2 mb-4">
         <div class="row justify-content-lg-center">
            <div class="col-md-4">
            <c:if test="${category != null }">
                <form method="post" action="update_category" onSubmit="vlidateForm()">
                <input type="hidden" name="id" value="${category.categoryId }">
            </c:if>
            <c:if test="${category == null }">
                <form method="post" action="create_category" onSubmit="validateForm()">
            </c:if>
            
                <div class="from-group mb-2">
                   <label for="name">Name: </label>
                   <input type="text" id="name" name="name" class="form-control" value="${category.name }">
                </div>
                <div align="center">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <button type="button" class="btn btn-danger" onClick="javascript:history.go(-1);">Cancel</button>
                </div>
            </form>
                
            </div>
         </div>
      </div>
      
       
      
       <jsp:directive.include file="footer.jsp" />
       
       

</body>
   <script type="text/javascript">
         function vlidateForm() {
        	var nameId = document.getElementById("name");
        	if(nameId.value.length === 0) {
        		alert("the name was required")
        		nameId.focus();
        		return false;
        	}
        	return true;
         }
   </script>
</html>