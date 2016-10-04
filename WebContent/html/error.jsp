<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Juliana Goh & Negar Ahmadifard">
        <title>JSP Page</title>
        <script>
            setTimeout(function(){
               window.location='form.html';
            }, 5000);
        </script>
        <link href="../css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="../css/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>   
            <ul>
              <li><a class="active" href="../">Home</a></li>
              <li><a href="#">BookGame</a></li>
              <li class="dropdown">
                <a href="#" class="dropbtn">Extra info</a>
                <div class="dropdown-content">
                  <a href="#">Help</a>
                  <a href="#">FAQ</a>
                  <a href="#">Contact us</a>
                </div>
              </li>
            </ul>
        </div>
        
        <a href="../includes/logout.jsp"></a>
        
        <h1>Error, user not found</h1>
        <h2>You will be directed back to the Register/Log-in page after five seconds</h2>
    </body>
</html>