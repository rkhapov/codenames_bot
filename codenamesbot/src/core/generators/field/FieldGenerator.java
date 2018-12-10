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
        cardsGenerator,
        Constants.FIELD_HEIGHT, Constants.FIELD_WIDTH,
        Constants.BLUE_WORDS_COUNT, Constants.RED_WORDS_COUNT),
        Constants.FIELD_HEIGHT, Constants.FIELD_WIDTH);
  }

  private List<Card> generate(
      ICardsGenerator cardsGenerator,
      int height, int width, int blue, int red) throws GeneratorException {
    var amount = height * width;
    var cards = cardsGenerator.getCards(amount, blue, red);

    return cards;
  }
}