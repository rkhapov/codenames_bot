package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;

public class StartNewGameCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public StartNewGameCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {
    var session = gameServer.startNew();

    return ExecutionResult.create(String.format("New game started. ID: %s", session.getId()));
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
