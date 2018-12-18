package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.Rank;

public class OpenCardCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public OpenCardCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {
    var word = arguments.getArgument("word");
    var user = gameServer.getUserByName(callerUserName);
    if (user.getRank() == Rank.CAPTAIN) {

      return ExecutionResult.create("You cannot open cards!");
    }

    var session = gameServer.getSessionByUser(user);

    var game = session.getGame();
    if (game.getNextTurnColor() != user.getColor()) {
      return ExecutionResult.create("It is not your turn");
    }
    if (!game.hasCard(word)) {
      return ExecutionResult.create("There is no such word");
    }

    return ExecutionResult.create(
        String.format("Successfully opened card. Color: %s", game.openCard(word).getColor()));
  }

  @Override
  public String getName() {
    return "/open";
  }

  @Override
  public String getFormat() {
    return "/open $word";
  }

  @Override
  public String getHelp() {
    return "opens card with given word";
  }
}
