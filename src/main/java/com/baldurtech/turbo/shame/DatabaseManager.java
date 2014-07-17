package com.baldurtech.turbo.shame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;

    public DatabaseManager execute(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baldurcontacts?user=baldurtech&password=baldurtechpwd");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return this;
    }

    public void close() {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch(Exception e) {

            }
        }

        if(statement != null) {
            try {
                statement.close();
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
