package core.commands;

import core.primitives.User;
import java.util.List;

public class GroupMessage {

  private final List<User> receivers;
  private final String message;

  public GroupMessage(List<User> receivers, String message) {
    this.receivers = receivers;
    this.message = message;
  }

  public List<User> getReceivers() {
    return receivers;
  }

  public String getMessage() {
    return message;
  }
}
