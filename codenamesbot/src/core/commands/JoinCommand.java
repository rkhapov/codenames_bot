package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;

public class JoinCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public JoinCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var id = arguments.getArgument("id");
    var mode = arguments.getArgument("mode");
    var targetSession = gameServer.getSessionById(id);



  }

  @Override
  public String getName() {
    return "/join";
  }

  @Override
  public String getFormat() {
    return "/join $id $mode";
  }
}
