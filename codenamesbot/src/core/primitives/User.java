package core.primitives;

import core.game.server.Session;

public class User {
  private final String name;
  private Session currentSession;

  public User(String name) {
    this.name = name;
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
