

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServletKaseya")
public class MyServletKaseya extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletKaseya() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		  String username = request.getParameter("username");
	      String password = request.getParameter("password");
	      authentication(username, password, response);
	}
	
	void authentication (String username, String password, HttpServletResponse response) throws IOException{
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	    try {
	         DBConnectionKaseya.getDBConnection(getServletContext());
	         connection = DBConnectionKaseya.connection;
	         String selectSQL = "SELECT * FROM USERS WHERE username LIKE ? AND password LIKE ?";
	         String theUserName = username + "%";
	         String thePassWord = password + "%";
	         preparedStatement = connection.prepareStatement(selectSQL);
	         preparedStatement.setString(1, theUserName);
	         preparedStatement.setString(2, thePassWord);
	         ResultSet rs = preparedStatement.executeQuery();
	         if(rs.next()==false) {
	        	 out.println("<BR><BR><BR><BR><BR>");               
	                out.println("<html><head><title>Login check</title></head><body>");
	    out.println("<CENTER><B>Unknown User</B>");
	    out.println("<BR><BR>");
	    out.println("<h3> Access Denied</h3></CENTER>");
	    out.println("<BR><BR><BR>");
	    out.println("<CENTER><Input Type=Button Value=Back></CENTER>");
	    out.println("</body></html>");
	         }
	         else {
	        	 response.sendRedirect("Welcome.html");
	         }
	         
	         
	        }catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	           e.printStackTrace();
	        } finally {
	           try {
	              if (preparedStatement != null)
	                 preparedStatement.close();
	           } catch (SQLException se2) {
	           }
	           try {
	              if (connection != null)
	                 connection.close();
	           } catch (SQLException se) {
	              se.printStackTrace();
	           }
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
