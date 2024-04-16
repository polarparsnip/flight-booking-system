package flight.serviceLayers;

import java.sql.ResultSet;
import java.sql.SQLException;

import flight.classes.User;
import flight.database.Database;

public class UserServiceLayer {

    private final String databasePath = "sql/flightBookingSystem.db"; 

    
    public void createUser(User user) {
      Database db = new Database(databasePath);
      db.open();

      String query = "INSERT OR IGNORE INTO Users (userId, name) VALUES (?, ?);";
      String[] values = {user.getId(), user.getName()};

      db.query(
        query, 
        values,
        false
      );

      db.close();
    }

    public User searchUserById(String userId) {

      Database db = new Database(databasePath);
      db.open();

      String query = "SELECT * from Users WHERE userId = ?;";
      String[] values = {userId};

      User p = null;

      try {
        ResultSet userRS = db.query(query, values, true);
        String name = userRS.getString("name");
        p = new User(userId, name);

      } catch(SQLException e) {
        System.err.println("Error searching for user: " + e);
        System.err.println(e.getErrorCode());
      }

      db.close();
    
      return p;
    };
    
}
