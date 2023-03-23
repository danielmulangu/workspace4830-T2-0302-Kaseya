

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet implementation class DisplayKaseya
 */
@WebServlet("/DisplayKaseya")
public class DisplayKaseya extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisplayKaseya() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Display(response);
	}
	void Display(HttpServletResponse response)throws IOException {
		 response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Database Result";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#ffffff\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    try {
	    	DBConnectionKaseya.getDBConnection(getServletContext());
	        connection = DBConnectionKaseya.connection;
	        String selectSQL = "SELECT * FROM EMPLOYEE";
	        preparedStatement = connection.prepareStatement(selectSQL);
	        
	        ResultSet rs = preparedStatement.executeQuery();
            
	        while (rs.next()) {
	            int id = rs.getInt("EMPLOYEE_ID");
	            String firstName = rs.getString("FIRST_NAME").trim();
	            String  lastName= rs.getString("LAST_NAME").trim();
	            String phone = rs.getString("PHONE").trim();
	            String department = rs.getString("DEPARTMENT").trim();
	            String email= rs.getString("EMAIL").trim();
	            String office = rs.getString("OFFICE_LOCATION").trim();
	            

	               out.println("EMPLOYEE_ID: " + id + ", ");
	               out.println("FIRST_NAME: " + firstName + ", ");
	               out.println("LAST_NAME: " + lastName + ", ");
	               
	               out.println("Phone: " + phone + ", ");
	               out.println("DEPARTMENT: " + department + ", ");
	               out.println("Email: " + email + ", ");
	               out.println("OFFICE_LOCATION: " + office + "<br>");
	               out.println("<br>");
	         }
	         out.println("<a href=/webproject-T2-0302-kaseya/Welcome.html>Homepage</a> <br>");
	         out.println("</body></html>");
	         rs.close();
	         preparedStatement.close();
	         connection.close();
	    }
	    catch (SQLException se) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
