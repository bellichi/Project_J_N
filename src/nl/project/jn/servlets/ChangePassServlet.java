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
 * @author: Juliana Goh & Negar Ahmadifard
 */

public class ChangePassServlet extends HttpServlet {

   
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
        String password = request.getParameter("password");
        
        String href = null;
        
        System.out.println(username + " " + password);
        
        try{
//          connects with the database
            MysqlDatabaseConnector.connectDB();
            if(username != null && !username.isEmpty() || password != null && !password.isEmpty()) {
 //           if (username.equals(null)&&password.equals(null)||username.equals("")&&password.equals("")) {
            	
            	UpdateMysqlTable ch = new UpdateMysqlTable();
            
            	ch.UpdatePass(username, password);

            	System.out.println("Password updated");
            	
            	href="/changepass.jsp";

            } else{
            	
            	href="/html/changepass.jsp";
            }
        	
            response.getWriter().write(href);
            
//            disconnects the database
            MysqlDatabaseConnector.disconnectDB();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ChangePassServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
