package tests.commands;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import core.commands.Arguments;
import core.commands.GetStateCommand;
import core.game.IGame;
import core.game.server.IGameServer;
import core.game.server.Session;
import core.graphics.IDrawerSelector;
import core.graphics.field.IFieldDrawer;
import core.primitives.Field;
import core.primitives.Rank;
import core.primitives.User;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetStateCommandTests {
  private IGameServer gameServer;
  private IDrawerSelector selector;
  private IGame game;
  private Field field;
  private Session session;
  private User userCaptain;
  private User userPlayer;
  private IFieldDrawer fieldDrawer;
  private GetStateCommand command;

  public GetStateCommandTests() {
  }

  @BeforeEach
  public void init() {
    gameServer = mock(IGameServer.class);
    game = mock(IGame.class);
    field = new Field(new ArrayList<>(), 0, 0);
    when(game.getField()).thenReturn(field);
    session = new Session(game, "id");
    userCaptain = new User("captain", session);
    userCaptain.setRank(Rank.CAPTAIN);
    userPlayer = new User("player", session);
    userPlayer.setRank(Rank.PLAYER);
    when(gameServer.getUserByName("captain")).thenReturn(userCaptain);
    when(gameServer.getUserByName("player")).thenReturn(userPlayer);
    selector = mock(IDrawerSelector.class);
    fieldDrawer = mock(IFieldDrawer.class);
    when(selector.getDrawerForRank(any())).thenReturn(fieldDrawer);
    when(fieldDrawer.getImage(any())).thenReturn(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
    command = new GetStateCommand(gameServer, selector);
  }

  @Test
  public void execute_withCaptainUser_returnsCorrectPicture() {
    var result = command.execute("captain", new Arguments());

    assertEquals(result.getImages().get(0), selector.getDrawerForRank(userCaptain.getRank()).getImage(field));
  }

  @Test
  public void execute_withPlayerUser_returnsCorrectPicture() {
    var result = command.execute("player", new Arguments());

    assertEquals(result.getImages().get(0), selector.getDrawerForRank(userPlayer.getRank()).getImage(field));
  }
}
