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

        ContactService contactService = new ContactService();

        if(request.getParameter("contactId") == null) {
            response.getWriter().println("Get all contacts.");

            for(Contact contact: contactService.getAllContacts()) {
                response.getWriter().println("Id: " + contact.getId());
                response.getWriter().println("Name: " + contact.getName());
                response.getWriter().println("Mobile: " + contact.getMobile());
                response.getWriter().println("Vpmn: " + contact.getVpmn());
                response.getWriter().println("Job: " + contact.getJob());
            }
        } else {
            response.getWriter().println("Get contact by id: " + request.getParameter("contactId"));

            Contact contact = contactService.getContactById(request.getParameter("contactId"));

            if(contact != null) {
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

}

class ContactService {
    public List<Contact> getAllContacts() {
        return findAllContactsBySql("select * from contact");
    }

    public List<Contact> findAllContactsBySql(String sql) {
        List<Contact> contacts = new ArrayList<Contact>();

        DatabaseManager db = createDatabaseConnectionAndExecute(sql);

        try {
            if(db.resultSet != null) {
                while(db.resultSet.next()) {
                    contacts.add(createContactFromResultSet(db.resultSet));
                }
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        closeDatabaseManager(db);

        return contacts;
    }

    public Contact getContactById(String id) {
        return findFirstContactBySql("select * from contact where id=" + id);
    }

    public Contact findFirstContactBySql(String sql) {
        List<Contact> contacts = findAllContactsBySql(sql);

        if(contacts.size() > 0) {
            return contacts.get(0);
        } else {
            return null;
        }
    }

    public Contact createContactFromResultSet(ResultSet rs)
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

    public DatabaseManager createDatabaseConnectionAndExecute(String sql) {
        DatabaseManager db = new DatabaseManager();
        db.execute(sql);
        return db;
    }

    public void closeDatabaseManager(DatabaseManager db) {
        db.close();
    }
}
