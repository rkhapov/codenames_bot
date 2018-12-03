package core.primitives;

import com.google.inject.Inject;
import core.Constants;
import core.generators.GeneratorException;
import core.generators.cards.ICardsGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tools.Pair;

public class Field {

  private List<List<Card>> field;
  private Map<String, Card> wordToCard;

  @Inject
  public Field(ICardsGenerator cardsGenerator) {
    try {
      var p = generate(cardsGenerator,
          Constants.FieldHeight, Constants.FieldWidth,
          Constants.BlueWordsCount, Constants.RedWordsCount);

      field = p.first();
      wordToCard = p.second();

    } catch (GeneratorException e) {
      //todo: make clean
      e.printStackTrace();
      System.exit(1);
    }
  }

  public Card getCard(String word) {
    return wordToCard.get(word);
  }

  public List<Card> getCards() {
    return new ArrayList<>(wordToCard.values());
  }

  public Card open(String word) {
    var card = getCard(word);

    card.open();

    return card;
  }

  private Pair<List<List<Card>>, Map<String, Card>> generate(
      ICardsGenerator cardsGenerator,
      int height, int width, int blue, int red) throws GeneratorException {
    var field = new ArrayList<List<Card>>(height);
    var cards = cardsGenerator.getCards(height * width, blue, red);
    var wordToCard = new HashMap<String, Card>();
    var iter = cards.iterator();

    for (var i = 0; i < height; i++) {
      field.add(new ArrayList<>(width));

      for (var j = 0; j < width; j++) {
        var card = iter.next();

        field.get(i).set(j, card);
        wordToCard.put(card.getWord(), card);
      }
    }

    return Pair.create(field, wordToCard);
  }
}
