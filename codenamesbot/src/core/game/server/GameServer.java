package core.game.server;

import com.google.inject.Inject;
import com.google.inject.Provider;
import core.game.IGame;
import core.primitives.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import tools.Utils;

public class GameServer implements IGameServer {

  private static final List<Character> idGenerationAlphabet =
      "abcdefghijklmnopqrstuvwxyz".chars().mapToObj(c -> (char) c).collect(Collectors.toList());

  private final Provider<IGame> gameProvider;
  private final Map<String, User> nameToUser;
  private final Map<String, Session> idToSession;

  @Inject
  public GameServer(Provider<IGame> gameProvider) {
    this.gameProvider = gameProvider;
    idToSession = new HashMap<>();
    nameToUser = new HashMap<>();
  }

  @Override
  public Session startNew() {
    var session = createNewSession();

    idToSession.put(session.getId(), session);

    return session;
  }

  @Override
  public Session getSessionById(String id) {
    return idToSession.get(id);
  }

  @Override
  public Set<Session> getSessions() {
    return new HashSet<>(idToSession.values());
  }

  @Override
  public User getUserByName(String name) {
    if (!nameToUser.containsKey(name)) {
      nameToUser.put(name, new User(name, null, null));
    }
    return nameToUser.get(name);
  }

  private Session createNewSession() {
    return new Session(createNewGame(), getNextSessionId());
  }

  private IGame createNewGame() {
    return gameProvider.get();
  }

  private String getNextSessionId() {
    var nextId = generateId();

    while (idToSession.containsKey(nextId)) {
      nextId = generateId();
    }

    return nextId;
  }

  private String generateId() {
    var len = Utils.getRandomInt(5, 8);
    var id = new StringBuilder();

    for (var i = 0; i < len; i++) {
      id.append(Utils.getRandomElement(idGenerationAlphabet));
    }

    return id.toString();
  }
}
