package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.graphics.IDrawerSelector;
import core.primitives.Rank;
import java.util.List;

public class GetPictureCommand implements ICommand {

  private final IGameServer gameServer;
  private final IDrawerSelector drawerSelector;

  @Inject
  public GetPictureCommand(IGameServer gameServer, IDrawerSelector drawerSelector) {
    this.gameServer = gameServer;
    this.drawerSelector = drawerSelector;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var user = gameServer.getUserByName(callerUserName);
    var rank = user.getRank();
    var field = user.getCurrentSession().getGame().getField();
    var drawer = drawerSelector.getDrawerForRank(rank);

    return new ExecuteResult(null, List.of(drawer.getImage(field)));
  }

  @Override
  public String getName() {
    return "/getpic";
  }

  @Override
  public String getFormat() {
    return "/getpic";
  }
}
