package flight.classes;


/**
 * The User class represents a user registered in the system.
 * It stores information such as the user's identification number, and name.
 */
public class User {
  private final String userId;
  private final String name;

  /**
   * User constructor
   * 
   * @param userId The identification number for the user being registered
   * @param name The name of the user being registered
   */
  public User(String userId, String name) {
    this.userId = userId;
    this.name = name;
  }


  /**
   * Gets the identification number of this user
   * 
   * @return The id for this user
   */
  public String getId() {
    return this.userId;
  }


  /**
   * Gets the name of this user
   * 
   * @return Name of this user
   */
  public String getName() {
    return this.name;
  }


  @Override
  public String toString() {
      return "Auðkenni: " + userId + ", Nafn: " + name;
  }

}
