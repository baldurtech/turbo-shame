package com.baldurtech.turbo.shame;

import java.util.List;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactService {
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
