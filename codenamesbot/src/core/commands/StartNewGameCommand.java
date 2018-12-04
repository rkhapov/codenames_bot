package core.commands;

import core.game.IGame;
import java.util.List;

public class StartNewGameCommand implements ICommand {

  @Override
  public CommandResult execute(IGame game, List<String> args) {
    game.restart();
    return new CommandResult("New game started!", null);
  }

  @Override
  public String getStringCommand() {
    return "/start";
  }
}
