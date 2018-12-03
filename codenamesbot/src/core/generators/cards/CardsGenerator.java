package core.generators.cards;

import com.google.inject.Inject;
import core.generators.GeneratorException;
import core.generators.words.IWordsGenerator;
import core.generators.words.dictionaries.DictionaryMode;
import core.primitives.Card;
import core.primitives.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardsGenerator implements ICardsGenerator {

  private final IWordsGenerator wordsGenerator;

  @Inject
  public CardsGenerator(IWordsGenerator wordsGenerator) {
    this.wordsGenerator = wordsGenerator;
  }

  @Override
  public List<Card> getCards(int total, int blue, int red) throws GeneratorException {
    if (!isValidSetOfCards(total, blue, red)) {
      throw new IllegalArgumentException("Invalid cards set");
    }

    var cards = getCardsForWords(
        wordsGenerator.getWords(total, DictionaryMode.Standard), blue, red);
    Collections.shuffle(cards);

    return cards;
  }

  private List<Card> getCardsForWords(List<String> words, int blue, int red) {
    var cards = new ArrayList<Card>();
    var size = words.size();

    for (var i = 0; i < size; i++) {
      var color = Color.Blue;

      if (i >= blue && i < blue + red)
        color = Color.Red;
      else if (i == blue + red)
        color = Color.Black;
      else if (i > blue + red)
        color = Color.White;

      cards.add(Card.create(words.get(i), color));
    }

    return cards;
  }

  private boolean isValidSetOfCards(int total, int blue, int red) {
    return total > 0 && blue > 0 && red > 0 && (blue + red + 1) <= total;
  }
}
