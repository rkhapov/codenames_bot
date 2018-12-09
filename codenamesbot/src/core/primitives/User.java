package core.primitives;

import core.game.server.Session;
import java.util.Random;

public class User {
  private final String name;
  private Session currentSession;
  private Rank rank;

  public User(String name, Rank rank) {
    this.rank = rank;
    this.name = name;
  }

  public Rank getRank(){
    return rank;
  }

  public String getName() {
    return name;
  }

  public void setCurrentSession(Session session) {
    currentSession = session;
  }

  public Session getCurrentSession() {
    return currentSession;
  }
}
