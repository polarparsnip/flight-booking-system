package hi.verkefni.classes;

public class Passenger {
  private final String passengerId;
  private final String name;
  private final String kennitala;

  public Passenger(String passengerId, String name, String kennitala) {
    this.passengerId = passengerId;
    this.name = name;
    this.kennitala = kennitala;
  }

  public String getId() {
    return this.passengerId;
  }

  public String getName() {
    return this.name;
  }

  public String getKennitala() {
    return this.kennitala;
  }

}
