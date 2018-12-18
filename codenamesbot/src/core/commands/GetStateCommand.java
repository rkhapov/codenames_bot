package core.commands;

import com.google.inject.Inject;
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
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {
    var user = gameServer.getUserByName(callerUserName);
    var rank = user.getRank();
    var session = gameServer.getSessionByUser(user);

    if (session == null) {
      return ExecutionResult.create("You should join to game first");
    }

    var game = session.getGame();
    var field = game.getField();
    var drawer = drawerSelector.getDrawerForRank(rank);
    var message = String.format("State: %s, next turn: %s",
        game.getState(),
        game.getNextTurnColor());
    var image = drawer.getImage(field);

    return ExecutionResult.createImagesWithMessage(message, List.of(image));
  }

  @Override
  public String getName() {
    return "/state";
  }

  @Override
  public String getFormat() {
    return "/state";
  }

  @Override
  public String getHelp() {
    return "sends info about session";
  }
}
