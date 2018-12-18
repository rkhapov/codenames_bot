package tests.unit.commands;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import core.commands.StartNewGameCommand;
import core.game.server.IGameServer;
import core.game.server.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StartCommandTests {
  private IGameServer server;
  private StartNewGameCommand startCommand;
  private Session session;

  @BeforeEach
  public void init() {
    server = mock(IGameServer.class);
    session = mock(Session.class);
    when(session.getId()).thenReturn("1234");
    when(server.startNew()).thenReturn(session);
    startCommand = new StartNewGameCommand(server);
  }

  @Test
  public void execute_shouldCallStartNewOnce() {
    startCommand.execute("user", null, null);
    verify(server, times(1)).startNew();
  }

  @Test
  public void execute_returnsCorrectMessage() {
    var result = startCommand.execute("user", null, null);
    assertThat(result.getMessage().substring(22)).isEqualTo("1234");
  }
}
