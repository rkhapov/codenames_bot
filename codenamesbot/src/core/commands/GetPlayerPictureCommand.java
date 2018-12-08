package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.graphics.field.IFieldDrawer;
import core.graphics.field.PlayerFieldDrawer;
import core.primitives.User;
import java.util.List;

public class GetPlayerPictureCommand implements ICommand {

  private final IFieldDrawer fieldDrawer;
  private final IGameServer gameServer;

  @Inject
  public GetPlayerPictureCommand(PlayerFieldDrawer fieldDrawer,
      IGameServer gameServer) {
    this.fieldDrawer = fieldDrawer;
    this.gameServer = gameServer;
  }

  @Override
  public CommandResult execute(String caller, List<String> args) {
    return null;
  }

  @Override
  public String getName() {
    return "/getp";
  }
}
