package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.graphics.IDrawerSelector;

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
    var rank = server.getUserByName(callerUserName).getRank();
    if (rank == Rank.CAPTAIN)

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
