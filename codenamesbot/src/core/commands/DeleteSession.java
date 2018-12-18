package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;

public class DeleteSession implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public DeleteSession(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {
    var session = gameServer.getSessionById(arguments.getArgument("id"));

    if (session == null) {
      return ExecutionResult.create("No that session");
    }

    gameServer.deleteSession(session.getId());

    return ExecutionResult.create("Successfully delete session " + session.getId());
  }

  @Override
  public String getName() {
    return "/delete";
  }

  @Override
  public String getFormat() {
    return "/delete $id";
  }

  @Override
  public String getHelp() {
    return "delete existing session";
  }
}
