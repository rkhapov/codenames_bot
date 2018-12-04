package core.generators.field;

import com.google.inject.Inject;
import core.Constants;
import core.generators.GeneratorException;
import core.generators.cards.ICardsGenerator;
import core.primitives.Card;
import core.primitives.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldGenerator implements IFieldGenerator {

  private final ICardsGenerator cardsGenerator;

  @Inject
  public FieldGenerator(ICardsGenerator cardsGenerator) {
    this.cardsGenerator = cardsGenerator;
  }

  public Field generate() throws GeneratorException {
    return new Field(generate(
        cardsGenerator, Constants.FieldHeight, Constants.FieldWidth,
        Constants.BlueWordsCount, Constants.RedWordsCount));
  }

  private List<List<Card>> generate(
      ICardsGenerator cardsGenerator,
      int height, int width, int blue, int red) throws GeneratorException {
    var field = new ArrayList<List<Card>>(height);
    var cards = cardsGenerator.getCards(height * width, blue, red);

    for (var i = 0; i < height; i++) {
      field.add(new ArrayList<>(width));

      for (var j = 0; j < width; j++) {
        var card = cards.get(i * height + j);

        field.get(i).add(card);
      }
    }

    return field;
  }
}