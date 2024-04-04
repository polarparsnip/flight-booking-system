package hi.verkefni.classes;

public class Passenger {
  private final String passengerId;
  private final String name;
  private final String kennitala;

  /**
   * Passenger constructor
   * 
   * @param passengerId The identification number for the passenger being registered
   * @param name The name of the passenger being registered
   * @param kennitala The kennitala of the passenger being registered
   */
  public Passenger(String passengerId, String name, String kennitala) {
    this.passengerId = passengerId;
    this.name = name;
    this.kennitala = kennitala;
  }


  /**
   * Gets the identification number of this passenger
   * 
   * @return The id for this passenger
   */
  public String getId() {
    return this.passengerId;
  }


  /**
   * Gets the name of this passenger
   * 
   * @return Name of this passenger
   */
  public String getName() {
    return this.name;
  }


  /**
   * Gets the kennitala of this passenger
   * 
   * @return Kennitala for this passenger
   */
  public String getKennitala() {
    return this.kennitala;
  }

}
