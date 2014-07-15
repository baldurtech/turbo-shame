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
        if(request.getParameter("contactId") == null) {
            response.getWriter().println("Get all contacts.");
        } else {
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
                rs = stmt.executeQuery("select * from contact where id=" + request.getParameter("contactId"));
                if(rs.next()) {
                    response.getWriter().println("Name: " + rs.getString("name"));
                    response.getWriter().println("Mobile: " + rs.getString("mobile"));
                    response.getWriter().println("Vpmn: " + rs.getString("vpmn"));
                    response.getWriter().println("Email: " + rs.getString("email"));
                    response.getWriter().println("HomeAddress: " + rs.getString("home_address"));
                    response.getWriter().println("OfficeAddress: " + rs.getString("office_address"));
                    response.getWriter().println("Memo: " + rs.getString("memo"));
                    response.getWriter().println("Job: " + rs.getString("job"));
                    response.getWriter().println("JobLevel: " + rs.getString("job_level"));
                } else {
                    response.getWriter().println("Contact not found.");
                }
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
}
