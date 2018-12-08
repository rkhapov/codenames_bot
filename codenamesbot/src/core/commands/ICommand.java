package core.commands;

import core.game.IGame;
import core.primitives.User;
import java.util.List;

public interface ICommand {
  CommandResult execute(String callerUserName, List<String> args);
  String getName();
}
