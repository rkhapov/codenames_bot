package core.generators.cards;

import core.generators.GeneratorException;
import core.primitives.Card;
import java.util.List;

public interface ICardsGenerator {
  List<Card> getCards(int total, int blue, int red) throws GeneratorException;
}
