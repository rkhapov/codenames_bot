package core.game.server;

import core.primitives.Color;
import core.primitives.Rank;
import core.primitives.User;
import java.util.Set;

public interface IGameServer {

  Session startNew();

  void deleteSession(String id);

  Session getSessionByUser(User user);

  Session getSessionById(String id);

  Set<Session> getSessions();

  Set<User> getUsers();

  User getUserByName(String name);

  void createNewUser(String name, Rank rank, Session session, Long chatId, Color color);
}
