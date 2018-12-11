package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.Rank;
import java.util.List;
import java.util.Random;

public class OpenCardCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public OpenCardCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var word = arguments.getArgument("word");
    var user = gameServer.getUserByName(callerUserName);
    if (user.getRank() == Rank.CAPTAIN) {
      return new ExecuteResult("You cannot open cards!");
    }

    var id = user.getCurrentSession();
    if (id == null) {
      return new ExecuteResult("There is no such session");
    }

    var session = gameServer.getSessionById(id.getId());
    var game = session.getGame();
    if (!game.hasCard(word)) {
      return new ExecuteResult("There is no such word");
    }

    return new ExecuteResult(
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
