package core.commands;

import core.game.IGame;
import core.graphics.FieldDrawer;
import java.util.List;

public class GetPlayerPictureCommand implements ICommand {

  @Override
  public CommandResult execute(IGame game, List<String> args) {
    return new CommandResult(null, List.of(FieldDrawer.getImageForPlayer(game.getField())));
  }

  @Override
  public String getStringCommand() {
    return "/getp";
  }
}
