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
    return null;
  }

  @Override
  public String getName() {
    return "/getpicture";
  }

  @Override
  public String getFormat() {
    return "/getpicture";
  }
}
