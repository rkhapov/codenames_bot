package core.commands;

import com.google.inject.Inject;
import core.game.server.IGameServer;
import core.primitives.Rank;
import core.primitives.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SayCommand implements ICommand {

  private final IGameServer gameServer;

  @Inject
  public SayCommand(IGameServer gameServer) {
    this.gameServer = gameServer;
  }

  @Override
  public ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId) {

    var user = gameServer.getUserByName(callerUserName);

    if (user.getRank() != Rank.CAPTAIN) {
      return ExecutionResult.create("You can't do this command because you are not captain");
    }
    var msg = arguments.getArgument("msg");

    return ExecutionResult
        .createOnlyGroupMessage(List.of(new GroupMessage(new ArrayList<>(gameServer.getUsers()), msg)));
  }

  @Override
  public String getName() {
    return "/say";
  }

  @Override
  public String getFormat() {
    return "/say $msg";
  }

  @Override
  public String getHelp() {
    return "Says smth to your team";
  }
}
