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

    private List<Contact> getAllContacts() {
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

    private Contact getContactById(String id) {
        List<Contact> contacts = findAllContactsBySql("select * from contact where id=" + id);

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
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        DatabaseManager db = new DatabaseManager();
        try {
            db.connection = DriverManager.getConnection("jdbc:mysql://localhost/baldurcontacts?user=baldurtech&password=baldurtechpwd");
            db.statement = db.connection.createStatement();
            db.resultSet = db.statement.executeQuery(sql);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return db;
    }

    public void closeDatabaseManager(DatabaseManager db) {
        if(db.resultSet != null) {
            try {
                db.resultSet.close();
            } catch(Exception e) {

            }
        }

        if(db.statement != null) {
            try {
                db.statement.close();
            } catch(Exception e) {

            }
        }

        if(db.connection != null) {
            try {
                db.connection.close();
            } catch(Exception e) {

            }
        }
    }
}

class DatabaseManager {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
}
