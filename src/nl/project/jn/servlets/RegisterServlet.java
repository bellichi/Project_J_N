/**
     * Uitleg class
     * @author: Juliana Goh & Negar Ahmadifard
     */

package nl.project.jn.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.project.jn.database.MysqlDatabaseConnector;
import nl.project.jn.database.RetrieveMysqlData;
import nl.project.jn.database.UpdateMysqlTable;

/**
 * @author: Negar
 */

public class RegisterServlet extends HttpServlet {

   
	private static final long serialVersionUID = 1L;

	/**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        gets the parameter value given from AJAX
        String username = request.getParameter("user");
        String name = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");        
        String password = request.getParameter("password");

        String href = null;
            
        System.out.println(username + " " + name + " " + lastName + " " + email + " " + password);
        
        try{
//         connects with the database
           MysqlDatabaseConnector.connectDB();
           
		   // Checks first whether the username is already taken. 
           // If the username is already taken, a message will be alerted. 
           // If the username isn't taken, it will then check whether the emailaddress is already in use.
           // If the email is already in use, a message will be alerted. 
           // If both the username and the email aren't in use, registration will be complete. 
        
           RetrieveMysqlData ch = new RetrieveMysqlData();

           boolean checkuser = ch.CheckUser(username);
           
           if(checkuser == false) {
        	   boolean checkemail = ch.CheckEmail(email);
        	   
        	   if(checkemail == false) {
        		   
		           UpdateMysqlTable ut = new UpdateMysqlTable();
		           ut.RegisterUser(username, name, lastName, email, password);    
		           
		           System.out.println("Registration completed.");
		           
		           href="complete";

        	   } else {
		           href="This email is already in use. Please use another emailaddress.";
		           System.out.println("This email is already in use. Please use another emailaddress.");
        	   }
           }  else {
	           href="This username is already taken. Please choose another username.";
	           System.out.println("This username is already taken. Please choose another username.");
           }
           response.getWriter().write(href);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        
        } finally {
        	
//          disconnects the database
          MysqlDatabaseConnector.disconnectDB();
          System.out.println("Database disconnected.");
        }
    }

}
