package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.User;
import java.util.List;

public class OpenCardCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public OpenCardCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public CommandResult execute(String caller, List<String> args) {
    return null;
  }

  @Override
  public String getName() {
    return "/open";
  }
}
