package core.commands;

import core.generators.GeneratorException;
import core.generators.cards.CardsGenerator;
import core.generators.field.FieldGenerator;
import core.generators.words.WordsGenerator;
import core.graphics.FieldDrawer;
import java.util.List;

public class GetPlayerPictureCommand implements ICommand {

  @Override
  public CommandResult execute() {
    try {
      var fieldGen = new FieldGenerator(new CardsGenerator(new WordsGenerator()));
      var field = fieldGen.generate();
      var img = FieldDrawer.getImageForPlayer(field);
      return new CommandResult("AAA", List.of(img));
    } catch (GeneratorException e) {
      //s
    }
    return null;
  }

  @Override
  public String getStringCommand() {
    return "/getp";
  }
}
