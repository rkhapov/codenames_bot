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
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {
    try {
      var id = arguments.getArgument("id");
      var targetSession = gameServer.getSessionById(id);
      if (targetSession == null) {

        return ExecutionResult.create("There is no such session");
      }

      var rank = Rank.valueOf(arguments.getArgument("rank").toUpperCase());

      if (gameServer.getUsers().stream().filter(user -> user.getRank() == Rank.CAPTAIN).count() == 2
          && rank == Rank.CAPTAIN) {
        return ExecutionResult.create("There are exactly 2 captains in the session already");
      }

      gameServer.createNewUser(callerUserName, rank, targetSession, chatId);

    } catch (IllegalArgumentException e) {
      return ExecutionResult.create("Ranks: captain | player");
    }

    return ExecutionResult.create("You have successfully joined");
  }

  @Override
  public String getName() {
    return "/join";
  }

  @Override
  public String getFormat() {
    return "/join $id $rank";
  }

  @Override
  public String getHelp() {
    return "joins to an existing session";
  }
}
