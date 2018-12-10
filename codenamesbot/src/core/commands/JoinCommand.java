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
    try {
      var id = arguments.getArgument("id");
      var targetSession = gameServer.getSessionById(id);
      if (targetSession == null) {
        return new ExecuteResult("There is no such session", null);
      }
      var user = gameServer.getUserByName(callerUserName);
      var rank = Rank.valueOf(arguments.getArgument("rank").toUpperCase());
      user.setRank(rank);
      user.setCurrentSession(targetSession);
    } catch (IllegalArgumentException e) {
      return new ExecuteResult("Ranks: captain | player", null);
    }
    return new ExecuteResult("You have successfully joined", null);
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
