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

            for(Object obj: getAllContacts()) {
                Map contact = (Map) obj;

                response.getWriter().println("Id: " + contact.get("id"));
                response.getWriter().println("Name: " + contact.get("name"));
                response.getWriter().println("Mobile: " + contact.get("mobile"));
                response.getWriter().println("Vpmn: " + contact.get("vpmn"));
                response.getWriter().println("Job: " + contact.get("job"));
            }
        } else {
            response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

            Map contact = getContactById(request.getParameter("contactId"));

            if(contact.get("id") != null) {
                response.getWriter().println("Name: " + contact.get("name"));
                response.getWriter().println("Mobile: " + contact.get("mobile"));
                response.getWriter().println("Vpmn: " + contact.get("vpmn"));
                response.getWriter().println("Email: " + contact.get("email"));
                response.getWriter().println("HomeAddress: " + contact.get("homeAddress"));
                response.getWriter().println("OfficeAddress: " + contact.get("officeAddress"));
                response.getWriter().println("Memo: " + contact.get("memo"));
                response.getWriter().println("Job: " + contact.get("job"));
                response.getWriter().println("JobLevel: " + contact.get("jobLevel"));
            } else {
                response.getWriter().println("Contact not found!");
            }
        }
    }

    private List getAllContacts() {
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
            }
        } catch(SQLException sqle) {
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
        return contacts;
    }

    private Map getContactById(String id) {
        Map contact = new HashMap();

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
            rs = stmt.executeQuery("select * from contact where id=" + id);
            if(rs.next()) {
                contact.put("id", rs.getLong("id"));
                contact.put("name", rs.getString("name"));
                contact.put("mobile", rs.getString("mobile"));
                contact.put("vpmn", rs.getString("vpmn"));
                contact.put("email", rs.getString("email"));
                contact.put("homeAddress", rs.getString("home_address"));
                contact.put("officeAddress", rs.getString("office_address"));
                contact.put("memo", rs.getString("memo"));
                contact.put("job", rs.getString("job"));
                contact.put("jobLevel", rs.getString("job_level"));
            }
        } catch(SQLException sqle) {
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

        return contact;
    }
}
