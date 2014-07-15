package com.baldurtech.turbo.shame;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
            List contacts = new ArrayList();

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
                rs = stmt.executeQuery("select * from contact");
                while(rs.next()) {
                    Map contact = new HashMap();

                    contact.put("id", rs.getLong("id"));
                    contact.put("name", rs.getString("name"));
                    contact.put("mobile", rs.getString("mobile"));
                    contact.put("vpmn", rs.getString("vpmn"));
                    contact.put("job", rs.getString("job"));

                    contacts.add(contact);

                    response.getWriter().println("Id: " + contact.get("id"));
                    response.getWriter().println("Name: " + contact.get("name"));
                    response.getWriter().println("Mobile: " + contact.get("mobile"));
                    response.getWriter().println("Vpmn: " + contact.get("vpmn"));
                    response.getWriter().println("Job: " + contact.get("job"));

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
        } else {
            response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

            Long id = null;
            String name = null;
            String mobile = null;
            String vpmn = null;
            String email = null;
            String homeAddress = null;
            String officeAddress = null;
            String memo = null;
            String job = null;
            String jobLevel = null;

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
                    id = rs.getLong("id");
                    name = rs.getString("name");
                    mobile = rs.getString("mobile");
                    vpmn = rs.getString("vpmn");
                    email = rs.getString("email");
                    homeAddress = rs.getString("home_address");
                    officeAddress = rs.getString("office_address");
                    memo = rs.getString("memo");
                    job = rs.getString("job");
                    jobLevel = rs.getString("job_level");
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

            if(id != null) {
                response.getWriter().println("Name: " + name);
                response.getWriter().println("Mobile: " + mobile);
                response.getWriter().println("Vpmn: " + vpmn);
                response.getWriter().println("Email: " + email);
                response.getWriter().println("HomeAddress: " + homeAddress);
                response.getWriter().println("OfficeAddress: " + officeAddress);
                response.getWriter().println("Memo: " + memo);
                response.getWriter().println("Job: " + job);
                response.getWriter().println("JobLevel: " + jobLevel);
            } else {
                response.getWriter().println("Contact not found!");
            }
        }
    }
}
