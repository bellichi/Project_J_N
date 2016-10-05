/* 
 * 
 * author: Juliana Goh & Negar Ahmadifard
 */


// This function performs form validation. It checks whether a form field is empty. 
// Data of a form field is taken by the form name and the field name and saved in a parameter.
// If a form field is empty, this function alerts a message, and returns false. This prevents the form from being submitted.
// When all fields are filled, it calls the method performAjaxSend().
function validateLogForm() {
    var usernameLogin = document.forms["logForm"]["userLogin"].value;
    var passwordLogin = document.forms["logForm"]["passwLogin"].value;
    	
	if (usernameLogin === null || usernameLogin === "")  {
		alert("Username is missing");
        return false;
	} else
	
	if (passwordLogin === null || passwordLogin === "")  {
		alert("Password is missing");
        return false;
	} else {		
		performAjaxSend();
	}	
}

// This function is used to receive data from the form and send it to the servlet LoginServlet.
// Data of a form field is taken by its ID and saved in a parameter. 
function performAjaxSend() {
    var usernameLogin = document.getElementById("userLogin").value;
    var passwordLogin = document.getElementById("passwLogin").value;
      
    alert(usernameLogin + ", " + passwordLogin);

// Ajax call, where the parameters are send to the Servlet.   
    $.ajax({
        url:'../LoginServlet',
        data:{user:usernameLogin, passwordUser:passwordLogin},
        type:'POST',
        cache:false,
        success:function(data){
//            alert(data);
            
           if(data === "yes"){
               alert(data);
               window.location.replace("../AdminServlet");
               alert("next");
           
           }else if( data === "no"){
                window.location.replace("../html/user.jsp");
           } else{
               alert("user does not exist, please register");
//                window.location.replace("../html/error.jsp");
//                
                setTimeout(function(){
                    window.location.reload(1);
                }, 100);
//               
           }
        },
        error:function(result){
        	alert("user niet gevonden");
//        	alert(result.status + ' ' + result.statusText);
        }
    });

}

// This function performs form validation. It checks whether a form field is empty. 
// Data of a form field is taken by the form name and the field name and saved in a parameter.
// If a form field is empty, this function alerts a message, and returns false. This prevents the form from being submitted.
// When all fields are filled, it calls the method performAjaxRegSend().
function validateRegForm() {
    var username = document.forms["regForm"]["username"].value;
    var name = document.forms["regForm"]["name"].value;
    var lastName = document.forms["regForm"]["lastName"].value;
    var email = document.forms["regForm"]["email"].value;
    var password = document.forms["regForm"]["password"].value;
    var confirmpassword = document.forms["regForm"]["confirmpassword"].value;
    	
    if (username === null || username === "")  {
		alert("Username is missing");
        return false;
	} else
	
	if (name === null || name === "")  {
		alert("First Name is missing");
        return false;
	} else
	
	if (lastName === null || lastName === "")  {
		alert("Last Name is missing");
        return false;
	} else
	
	if (email === null || email === "")  {
		alert("Email is missing");
        return false;
	} else
	
	if (password === null || password === "")  {
		alert("Password is missing");
        return false;
	} else
		
	if (confirmpassword !== password)  {
		alert("Please confirm your password");
        return false;
	} else {		
		performAjaxRegSend();
	}	
}		


//This function is used to receive data from form and send it to the servlet RegisterServlet.
//Data of a form field is taken by its ID and saved in a parameter  
function performAjaxRegSend() {		  
 
        var username = document.getElementById("username").value;
        var name = document.getElementById("name").value;
        var lastName = document.getElementById("lastName").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;	

    alert(username + " " + name + " " + lastName + " " + email);
    
// Ajax call, where the parameters are send to the Servlet.   
    $.ajax({
        url:'../RegisterServlet',
        data:{user:username, firstName:name, lastName:lastName, email:email, password:password},
        type:'POST',
        cache:false,
        success:function(data){
        	if(data === "complete") {
                    window.location.replace("registered.jsp");
                        
        	} else {
                    alert(data);
        	}
        },
        error:function(result){
        	alert("er gaat alsnog iets mis");
        	alert(result.status + ' ' + result.statusText);
        }
     });
  
	}
	
//This function performs form validation. It checks whether a form field is empty. 
//Data of a form field is taken by the form name and the field name and saved in a parameter.
//If a form field is empty, this function alerts a message, and returns false. This prevents the form from being submitted.
//When all fields are filled, it calls the method performPassChange().
function validateUpdateForm() {
 var username = document.forms["updateForm"]["chusername"].value;
 var password = document.forms["updateForm"]["chpassword"].value;
 var conpassword = document.forms["updateForm"]["conpassword"].value;
 	
	if (username === null || username === "")  {
		alert("Username is missing");
		return false;     
	} else	
		
	if (password === null || password === "")  {
		alert("Password is missing");
		return false;    
	} else 
	
	if (conpassword !== password)  {
		alert("Please confirm your password");
	    return false;
	} else {
		performAjaxPassChange();
	}	
}	

//  This function is used to receive data from form and send it to the servlet ChangePassServlet
//  Data of a form field is taken by its ID and saved in a parameter 
function performAjaxPassChange() {
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
        window.location.replace(data);
      },
      error:function(result){
      	alert("er gaat alsnog iets mis");
      	alert(result.status + ' ' + result.statusText);
      }
   });


function logoutButton() {
    window.location.replace("../index.html");
    };
}