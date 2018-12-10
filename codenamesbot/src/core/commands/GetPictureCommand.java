package core.commands;

import core.game.server.IGameServer;
import core.graphics.field.IFieldDrawer;
import core.primitives.Rank;

public class GetPictureCommand implements ICommand {
  private final IGameServer server;
  priva

  public GetPictureCommand(IGameServer server,
      IFieldDrawer fieldDrawer) {
    this.server = server;
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
