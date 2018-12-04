package core.commands;

import core.game.IGame;
import core.graphics.FieldDrawer;
import java.util.List;

public class OpenCardCommand implements ICommand {

  @Override
  public CommandResult execute(IGame game, List<String> args) {
    game.openCard(args.get(1));
    return new CommandResult("card opened", null);
  }

  @Override
  public String getStringCommand() {
    return "/open";
  }
}
