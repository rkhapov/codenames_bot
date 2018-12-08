package core.game.server;

import core.game.IGame;
import core.primitives.User;

public class Session {

  private final User redCaptain;
  private final User blueCaptain;
  private final IGame game;
  private final String id;

  public Session(IGame game, User redCaptain, User blueCaptain, String id) {
    if (id == null) {
      throw new IllegalArgumentException("id of session cant be null");
    }

    this.game = game;
    this.redCaptain = redCaptain;
    this.blueCaptain = blueCaptain;
    this.id = id;
  }

  public User getRedCaptain() {
    return redCaptain;
  }

  public User getBlueCaptain() {
    return blueCaptain;
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
