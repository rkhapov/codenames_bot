package core.game.server;

import core.primitives.User;
import java.util.Set;

public interface IGameServer {

  Session startNew();

  void deleteSession(String id);

  Session getSessionById(String id);

  Set<Session> getSessions();

  User getUserByName(String name);
}
