package core.primitives;

public class User {
  private final String name;
  private final Rank rank;

  public User(String name, Rank rank) {
    this.name = name;
    this.rank = rank;
  }

  public Rank getRank(){
    return rank;
  }

  public String getName() {
    return name;
  }
}
