package tests.commands;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import core.commands.Arguments;
import core.commands.GetPictureCommand;
import core.game.IGame;
import core.game.server.IGameServer;
import core.game.server.Session;
import core.graphics.IDrawerSelector;
import core.graphics.field.IFieldDrawer;
import core.primitives.Card;
import core.primitives.Field;
import core.primitives.Rank;
import core.primitives.User;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetPictureCommandTests {

  @BeforeEach
  public void init() {

  }

  @Test
  public void execute_withCaptainUser_shouldCallCaptainDrawer() {
    var gameServer = mock(IGameServer.class);
    var game = mock(IGame.class);
    var field = new Field(new ArrayList<>(), 0, 0);
    when(game.getField()).thenReturn(field);
    var session = new Session(game, "id");
    var loh = new User("loh", Rank.CAPTAIN, session);
    when(gameServer.getUserByName(any())).thenReturn(loh);
    var selector = mock(IDrawerSelector.class);
    var fieldDrawer = mock(IFieldDrawer.class);
    when(selector.getDrawerForRank(Rank.CAPTAIN)).thenReturn(fieldDrawer);
    when(fieldDrawer.getImage(any())).thenReturn(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
    var command = new GetPictureCommand(gameServer, selector);

    command.execute("sdfdfsd", new Arguments());

    verify(selector, times(1)).getDrawerForRank(Rank.CAPTAIN);
  }
}
