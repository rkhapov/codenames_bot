package core.game.server;

import core.primitives.User;
import java.util.Set;

public interface IGameServer {

  Session startNew(User redCaptain, User blueCaptain);

  Session getSessionById(String id);

  Set<Session> getSessions();
}
