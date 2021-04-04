<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
     <div>
        <img alt="adminLogo" src="../images/logo1.png">
     </div>
     
     <div>
        Welcome, ${email }| <a href="logout">Logout</a>
        <br><Br>
     </div>
     
     
     <div id="headermenu">
          <div class="element_menu" >
              <a href="list_users">
                 <img src="../images/users.png"><br>Users
              </a>
          </div>
          
          <div class="element_menu">
             <a href="list_category">
                 <img src="../images/category.png"><br>Categories
             </a> 
          </div>
          
          <div class="element_menu">
              <a href="list_book">
                  <img src="../images/bookstack.png"><br>Books
                
              </a> 
          </div>
          
          <div class="element_menu">
              <a href="list_customer">
                  <img src="../images/customer.png"><br>Customers
                
              </a> 
          </div>
          
          <div class="element_menu">
              <a href="list_review">
                  <img src="../images/review.png"><br>Reviews
                
              </a> 
          </div>
          
          <div>
              <a href="orders">
                  <img src="../images/order.png"><br>Orders
              </a> 
          </div>
         
     </div>
</div>