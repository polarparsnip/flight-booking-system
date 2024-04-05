package hi.verkefni.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


/**
 * The Database class represents a simple SQLite database connection handler.
 * It provides methods for opening, closing, and checking the status of the connection.
 */
public class Database {
    private Connection connection;
    private final String connectionUrl;

    /**
     * Constructs a new Database object with the specified database file path.
     *
     * @param dbPath the file path to the SQLite database file
     */
    public Database(String dbPath) {
        connectionUrl = String.format("jdbc:sqlite:%s", dbPath);
    }


    /**
     * Opens a connection to the SQLite database if not already open.
     * If the connection is already open, this method does nothing.
     */
    public void open() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(connectionUrl);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    /**
     * Closes the connection to the SQLite database if it is open.
     * If the connection is already closed, this method does nothing.
     */
    public void close() {
        try {
          if (connection != null) {
            connection.close();
          }
        } catch (Exception e) {
          System.err.println(e);
        }
    }


    /**
     * Checks if the connection to the SQLite database is currently open.
     *
     * @return true if the connection is open, false otherwise
     */
    public boolean isOpen() {
        return connection != null;
    }


    /**
     * Executes a SQL query on the database with optional parameters.
     * 
     * @param query the SQL query to execute
     * @param values an array of parameter values to be bound to the query, or an empty array/null if no parameters are needed
     * @param selectStatement a boolean indicating whether the query is a SELECT statement or not
     * @return a ResultSet containing the results of the query if it is a SELECT statement, or null otherwise
     */
    public ResultSet query(String query, String[] values, Boolean selectStatement) {

        ResultSet result = null;
        
        if (values == null || values.length == 0) {

            try {
                Statement S = connection.createStatement();

                if (selectStatement) {
                    result = S.executeQuery(query);
                } else {
                    S.executeUpdate(query);
                }

                S.close();
                
            } catch (SQLException e) {
                System.err.println(e);
            }

        } else {

            try {
                PreparedStatement PS = connection.prepareStatement(query);
        
                for (int i = 1; i < values.length+1; i++) {
                    PS.setString(i, values[i]);
                }
                
                if (selectStatement) {
                    result = PS.executeQuery();
                } else {
                    PS.executeUpdate();
                }
                
                PS.close();
        
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

        return result;
    }

}