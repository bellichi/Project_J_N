/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * author: Juliana Goh & Negar Ahmadifard
 */

function performAjaxSend() {
//    This function is used to receive data from form and send it to the servlet LoginServlet

//    Data of a form field is taken by its ID and saved in a parameter 
    var usernameLogin = document.getElementById("userLogin").value;
    var passwordLogin = document.getElementById("passwLogin").value;
    
    
    alert(usernameLogin + ", " + passwordLogin);

//    Ajax call, where the parameters are send to the Servlet
//    $.ajax({
//        type: "POST",
//        url: '../LoginServlet',
//        cache: false,
//        contentType: false,
//        dataType: "text",
//        data: {
//            user:usernameLogin,
//            passwordUser:passwordLogin
//        },
//        success: function (data) {
//            alert('yay');
//        }
//    });
    
    $.ajax({
        url:'../LoginServlet',
        data:{user:usernameLogin, passwordUser:passwordLogin},
        type:'POST',
        cache:false,
        success:function(data){
           alert(data);
           window.location.replace("http://localhost:8080/Project_J_N" + data);
        },
        error:function(result){
        	alert("er gaat alsnog iets mis")
        	alert(result.status + ' ' + result.statusText);
        }
     }
);

}

function performAjaxRegSend() {
//    This function is used to receive data from form and send it to the servlet LoginServlet

//    Data of a form field is taken by its ID and saved in a parameter 
    var username = document.getElementById("username").value;
    var name = document.getElementById("name").value;
    var lastName = document.getElementById("lastName").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    
    alert(username + " " + name + " " + lastName + " " + email);

//    Ajax call, where the parameters are send to the Servlet
    $.ajax({
        type: "POST",
        url: '../RegisterServlet',
        cache: false,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
        dataType: "json",
        data: {
            user:username,            
            firstName:name,            
            lastName:lastName,
            email:email,
            password:password
        },
        success: function (data) {
            alert('yay');
        }
    });

}


function performAjaxPassChange() {
//  This function is used to receive data from form and send it to the servlet LoginServlet

//  Data of a form field is taken by its ID and saved in a parameter 
  var username = document.getElementById("chusername").value;
  var password = document.getElementById("chpassword").value;
  
  alert(username);
      
//  Ajax call, where the parameters are send to the Servlet
  $.ajax({
      url:'../ChangePassServlet',
      data:{user:username, password:password},
      type:'POST',
      cache:false,
      success:function(data){
         alert(data);
        window.location.replace("http://localhost:8080/Project_J_N" + data);
      },
      error:function(result){
      	alert("er gaat alsnog iets mis")
      	alert(result.status + ' ' + result.statusText);
      }
   });

}
