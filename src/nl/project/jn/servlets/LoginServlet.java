/**
 *
 * A servlet which receive the data from the ajax call in sendData.js (function called performAjaxSend)
 * 
 * 
 * @author Juliana Goh & Negar Ahmadifard
 */


package nl.project.jn.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import nl.project.jn.database.*;


public class LoginServlet extends HttpServlet {

    
    /**
	 * 
	 */
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
        String password = request.getParameter("passwordUser");
        
        System.out.println(username + " " + password);

        User user = new User();
        
        String href = null;

        try{
//            connects with the database
            MysqlDatabaseConnector.connectDB();

//            If username and password is not empty, check users Login
            if (username!=null&&password!=null) {
                System.out.println(username + " " + password);
                
                RetrieveMysqlData rd = new RetrieveMysqlData();
                
//                Sends the data to the LoginUser method in MysqlDatabaseConnector class to check if the user already is registrated
                rd.LoginUser(username, password, user);
                
                System.out.println(user.getUserAccess());
                
                if(user.getUserAccess().equals("yes")){
                    System.out.println("yaaayyy admin");
//                    RequestDispatcher view = request.getRequestDispatcher("/AdminServlet");
//                    view.forward(request, response);
                    
                    //Sends the generated script to the website page
                    href = "/AdminServlet";
                   
                } else{
                	 //Sends the generated script to the website page
                	href = "/html/user.jsp";
                }
            }    
            
            response.getWriter().print(href);
            
//            disconnects the database
            MysqlDatabaseConnector.disconnectDB();

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
