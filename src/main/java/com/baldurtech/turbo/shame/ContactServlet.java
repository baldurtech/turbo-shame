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

            for(Contact contact: getAllContacts()) {
                response.getWriter().println("Id: " + contact.getId());
                response.getWriter().println("Name: " + contact.getName());
                response.getWriter().println("Mobile: " + contact.getMobile());
                response.getWriter().println("Vpmn: " + contact.getVpmn());
                response.getWriter().println("Job: " + contact.getJob());
            }
        } else {
            response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

            Contact contact = getContactById(request.getParameter("contactId"));

            if(contact.getId() != null) {
                response.getWriter().println("Name: "          + contact.getName());
                response.getWriter().println("Mobile: "        + contact.getMobile());
                response.getWriter().println("Vpmn: "          + contact.getVpmn());
                response.getWriter().println("Email: "         + contact.getEmail());
                response.getWriter().println("HomeAddress: "   + contact.getHomeAddress());
                response.getWriter().println("OfficeAddress: " + contact.getOfficeAddress());
                response.getWriter().println("Memo: "          + contact.getMemo());
                response.getWriter().println("Job: "           + contact.getJob());
                response.getWriter().println("JobLevel: "      + contact.getJobLevel());
            } else {
                response.getWriter().println("Contact not found!");
            }
        }
    }

    private List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<Contact>();

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
                Contact contact = new Contact();

                contact.setId(rs.getLong("id"));
                contact.setName(rs.getString("name"));
                contact.setMobile(rs.getString("mobile"));
                contact.setVpmn(rs.getString("vpmn"));
                contact.setJob(rs.getString("job"));

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

    private Contact getContactById(String id) {
        Contact contact = new Contact();

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
                contact = getContactFromResultSet(rs);
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

    public Contact getContactFromResultSet(ResultSet rs)
        throws SQLException {

        Contact contact = new Contact();

        contact.setId(rs.getLong("id"));
        contact.setName(rs.getString("name"));
        contact.setMobile(rs.getString("mobile"));
        contact.setVpmn(rs.getString("vpmn"));
        contact.setEmail(rs.getString("email"));
        contact.setHomeAddress(rs.getString("home_address"));
        contact.setOfficeAddress(rs.getString("office_address"));
        contact.setMemo(rs.getString("memo"));
        contact.setJob(rs.getString("job"));
        contact.setJobLevel(rs.getInt("job_level"));

        return contact;
    }
}
