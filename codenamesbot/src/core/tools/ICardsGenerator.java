package core.tools;

import core.primitives.Card;
import java.util.Collection;

public interface ICardsGenerator {
  Collection<Card> getCards(int amount);
}
