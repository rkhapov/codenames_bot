package core.game.server;

import core.game.IGame;
import core.primitives.User;

public class Session {

  private final IGame game;
  private final String id;

  public Session(IGame game, String id) {
    if (id == null) {
      throw new IllegalArgumentException("id of session cant be null");
    }

    this.game = game;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public IGame getGame() {
    return game;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Session) {
      return id.equals(((Session) obj).id);
    }

    return false;
  }

  @Override
  public String toString() {
    return "Session " + id;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
