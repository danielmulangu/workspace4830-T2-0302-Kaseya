

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
 * Servlet implementation class DeleteKaseya
 */
@WebServlet("/DeleteKaseya")
public class DeleteKaseya extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteKaseya() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 String keyword = request.getParameter("keyword");
	     String keyword1 = request.getParameter("keyword1");
	      delete(keyword, keyword1,response);
	}
	void delete(String keyword,String keyword1, HttpServletResponse response) 
			throws IOException {
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Successulf Deletion";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#ffffff\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    PreparedStatement preparedSMT = null;
	    
	    try {
	    	
	    	DBConnectionKaseya.getDBConnection(getServletContext());
	        connection = DBConnectionKaseya.connection;
	        String deleteSQL = "DELETE FROM EMPLOYEE WHERE FIRST_NAME =? AND LAST_NAME =? ";
	        preparedStatement = connection.prepareStatement(deleteSQL);
	        preparedStatement.setString(1, keyword);
	        preparedStatement.setString(2, keyword1);
	       
	        int num_row = preparedStatement.executeUpdate();
	        
	        
	        String selectSQL =" SELECT * FROM EMPLOYEE";
            preparedSMT = connection.prepareStatement(selectSQL);
	        
	        ResultSet rs = preparedSMT.executeQuery();
            
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
