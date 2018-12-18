package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.Color;
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
      var color = Color.valueOf(arguments.getArgument("color").toUpperCase());
      var targetSession = gameServer.getSessionById(id);
      if (targetSession == null) {

        return ExecutionResult.create("There is no such session");
      }

      var rank = Rank.valueOf(arguments.getArgument("rank").toUpperCase());

      if (gameServer.getUsers().stream()
          .filter(user -> user.getRank() == Rank.CAPTAIN && user.getColor() == color).count() == 1
          && rank == Rank.CAPTAIN) {
        return ExecutionResult.create("Illegal number of captains");
      }

      gameServer.createNewUser(callerUserName, rank, targetSession, chatId, color);

    } catch (IllegalArgumentException e) {
      return ExecutionResult.create("Ranks: captain | player. Colors: red | blue");
    }

    return ExecutionResult.create("You have successfully joined");
  }

  @Override
  public String getName() {
    return "/join";
  }

  @Override
  public String getFormat() {
    return "/join $id $rank $color";
  }

  @Override
  public String getHelp() {
    return "joins to an existing session";
  }
}
