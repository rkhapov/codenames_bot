package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.Rank;

public class JoinCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public JoinCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var id = arguments.getArgument("id");
    var rank = Rank.valueOf(arguments.getArgument("rank").toUpperCase());
    var targetSession = gameServer.getSessionById(id);

    return null;
  }

  @Override
  public String getName() {
    return "/join";
  }

  @Override
  public String getFormat() {
    return "/join $id $rank";
  }
}
