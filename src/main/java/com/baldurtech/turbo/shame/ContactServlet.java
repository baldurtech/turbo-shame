package com.baldurtech.turbo.shame;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baldurcontacts?user=baldurtech&password=baldurtechpwd");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from contact where id=1");
            rs.next();
            response.getWriter().println(rs.getString("name"));
            connection.close();
        } catch(SQLException sqle) {
            response.getWriter().println("Cannot connect to DB.");
            response.getWriter().println(sqle.getMessage());
            sqle.printStackTrace();
        }

        if(rs != null) {
            try {
                rs.close();
            } catch(Exception e) {

            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch(Exception e) {

            }
        }

        if(connection != null) {
            try {
                connection.close();
            } catch(Exception e) {

            }
        }
    }
}
