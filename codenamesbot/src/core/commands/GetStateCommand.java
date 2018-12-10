package core.commands;

import com.google.inject.Inject;
import core.game.IGame;
import core.game.server.IGameServer;
import core.graphics.IDrawerSelector;
import java.util.List;

public class GetStateCommand implements ICommand {

  private final IGameServer gameServer;
  private final IDrawerSelector drawerSelector;

  @Inject
  public GetStateCommand(IGameServer gameServer, IDrawerSelector drawerSelector) {
    this.gameServer = gameServer;
    this.drawerSelector = drawerSelector;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var user = gameServer.getUserByName(callerUserName);
    var rank = user.getRank();
    var game = user.getCurrentSession().getGame();
    var field = game.getField();
    var drawer = drawerSelector.getDrawerForRank(rank);
    var message = String.format("State: %s, next turn: %s",
        game.getState(),
        game.getNextTurnColor());
    var image = drawer.getImage(field);

    return new ExecuteResult(message, List.of(image));
  }

  @Override
  public String getName() {
    return "/state";
  }

  @Override
  public String getFormat() {
    return "/state";
  }
}
