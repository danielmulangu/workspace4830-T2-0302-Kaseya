import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;

public class DBConnectionKaseya {
   static Connection connection = null;
   static ServletContext servletContext;

   static void getDBConnectionKaseya() {
      System.out.println("--MySQL Connection Test -");
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      System.out.println("Driver Registered!");

      connection = null;
      try {
         UtilPropKaseya.loadProperty(servletContext);
         connection = DriverManager.getConnection(getURL(), getUserName(), getPassword());
      } catch (Exception e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }

      if (connection != null) {
         System.out.println("Connection Established!");
      } else {
         System.out.println("Failed to make connection!");
      }
   }

   static String getURL() {
      String url = UtilPropKaseya.getProp("url");
      System.out.println("[DBG] URL: " + url);
      return url;
   }

   static String getUserName() {
      String usr = UtilPropKaseya.getProp("user");
      System.out.println("[DBG] URL: " + usr);
      return usr;
   }

   static String getPassword() {
      String pwd = UtilPropKaseya.getProp("password");
      System.out.println("[DBG] URL: " + pwd);
      return pwd;
   }

   public static void getDBConnection(ServletContext context) {
      servletContext = context;
      getDBConnectionKaseya();
   }
}
