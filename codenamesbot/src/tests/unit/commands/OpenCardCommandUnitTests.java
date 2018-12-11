package tests.unit.commands;

import static org.mockito.Mockito.mock;

import core.game.server.IGameServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpenCardCommandUnitTests {

  private IGameServer server;

  @BeforeEach
  public void init() {
    server = mock(IGameServer.class);


  }

  @Test
  public void execute() {

  }

}
