package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import java.util.List;

public class StartNewGameCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public StartNewGameCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var session = gameServer.startNew();
    return new ExecuteResult(String.format("New game started. ID: %s", session.getId()));
  }

  @Override
  public String getName() {
    return "/start";
  }

  @Override
  public String getFormat() {
    return "/start";
  }

  @Override
  public String getHelp() {
    return "starts new session";
  }
}
