package hi.verkefni.classes;

public class Passenger {
  private final String id;
  private final String name;
  private final String kennitala;

  public Passenger(String userId, String name, String kennitala) {
    id = userId;
    this.name = name;
    this.kennitala = kennitala;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getKennitala() {
    return this.kennitala;
  }

}
