package core.commands;

import core.game.IGame;
import java.util.List;

public interface ICommand {
  CommandResult execute(IGame game, List<String> args);
  String getName();
}
