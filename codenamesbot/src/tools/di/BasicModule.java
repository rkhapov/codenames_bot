package tools.di;

import com.google.inject.AbstractModule;
import core.generators.cards.CardsGenerator;
import core.generators.cards.ICardsGenerator;
import core.generators.field.FieldGenerator;
import core.generators.field.IFieldGenerator;
import core.generators.words.IWordsGenerator;
import core.generators.words.WordsGenerator;

public class BasicModule extends AbstractModule {

  @Override
  public void configure() {
    bind(IWordsGenerator.class).to(WordsGenerator.class);
    bind(ICardsGenerator.class).to(CardsGenerator.class);
    bind(IFieldGenerator.class).to(FieldGenerator.class);
  }
}
