package tests.commands;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import core.commands.Arguments;
import core.commands.JoinCommand;
import core.game.server.IGameServer;
import core.game.server.Session;
import core.primitives.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JoinCommandTests {
  private IGameServer server;
  private JoinCommand joincommand;
  private Session session;

  @BeforeEach
  public void init() {
    server = mock(IGameServer.class);
    session = mock(Session.class);
    when(session.getId()).thenReturn("1234");
    when(server.getSessionById("1234")).thenReturn(session);
    joincommand = new JoinCommand(server);
    when(server.getUserByName(any())).thenReturn(new User("user", session));
  }

  @Test
  public void execute_withNonExistentId_returnsCorrectWarningMessage() {
    var arguments = new Arguments();
    arguments.addArgument("id", "1");
    var result = joincommand.execute("user", arguments);
    assertThat(result.getMessage()).isEqualTo("There is no such session");
  }

  @Test
  public void execute_withIncorrectRank_returnsCorrectWarningMessage() {
    var arguments = new Arguments();
    arguments.addArgument("id", "1234").addArgument("rank", "ololo");
    var result = joincommand.execute("user", arguments);
    assertThat(result.getMessage()).isEqualTo("Ranks: captain | player");
  }


  @Test
  public void execute_withCorrectArguments_returnsCorrectMessage() {
    var arguments = new Arguments();
    arguments.addArgument("id", "1234").addArgument("rank", "player");
    var result = joincommand.execute("user", arguments);
    assertThat(result.getMessage()).isEqualTo("You have successfully joined");
  }
}
