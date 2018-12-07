package core.commands;

import com.google.inject.Inject;
import core.game.IGame;
import core.graphics.field.FieldDrawer;
import core.graphics.field.IFieldDrawer;
import core.graphics.field.PlayerFieldDrawer;
import java.util.List;

public class GetPlayerPictureCommand implements ICommand {

  private final IFieldDrawer fieldDrawer;

  @Inject
  public GetPlayerPictureCommand(PlayerFieldDrawer fieldDrawer) {
    this.fieldDrawer = fieldDrawer;
  }

  @Override
  public CommandResult execute(IGame game, List<String> args) {
    return new CommandResult(null, List.of(fieldDrawer.getImage(game.getField())));
  }

  @Override
  public String getName() {
    return "/getp";
  }
}
