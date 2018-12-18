package core.primitives;

import core.game.server.Session;

public class User {
  private final String name;
  private Rank rank;

  public User(String name) {
    this.rank = null;
    this.name = name;
  }

  public void setRank(Rank rank) {
    this.rank = rank;
  }

  public Rank getRank(){
    return rank;
  }

  public String getName() {
    return name;
  }
}
