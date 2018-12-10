package core.primitives;

import core.game.server.Session;
import java.util.Random;

public class User {
  private final String name;
  private Session currentSession;
  private Rank rank;

  public User(String name, Session session) {
    this.rank = null;
    this.name = name;
    currentSession = session;
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

  public void setCurrentSession(Session session) {
    currentSession = session;
  }

  public Session getCurrentSession() {
    return currentSession;
  }
}
