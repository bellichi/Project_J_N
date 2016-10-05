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
import nl.project.jn.database.UpdateMysqlTable;



/**
 * This servlet is called when an user wants to wants to change his/her password. 
 * Ajax sends the username's given values to the Servlet, which in turn sends the values to the database.
 * This class contains the method doPost().
 * 
 * @author: Julia Goh & Negar Ahmadifard
 */
public class ChangePassServlet extends HttpServlet {
    
    //A version control in a Serializable class
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
        
        // Gets the parameter values which are send by AJAX
        String username = request.getParameter("user");    
        String password = request.getParameter("password");
        
        // String, which contains the url for the redirected webpage.
        String href;
        
        System.out.println(username + " " + password);
        
        try{
            // Connecting with the database.
            MysqlDatabaseConnector.connectDB();
            
            /* 
            * Checks whether the fields username and password aren't null or empty.
            * When these two fields aren't empty, data will be send to the database and the user will get redirected to the changepass.jsp page.
            * When a field is empty or null, data won't be send to the database and the user gets redirected to the error.jsp page.
            */
            if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            	UpdateMysqlTable ch = new UpdateMysqlTable();
                //Sends the data to the method to update the password with the given username
            	ch.UpdatePass(username, password);
            	href="../html/changepass.jsp";

            } else{
            	href="../html/error.jsp";
            }
        	
            response.getWriter().write(href);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ChangePassServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Disconnects the database.
            MysqlDatabaseConnector.disconnectDB();    
        }
    }
}
