package tools.di;

import com.google.inject.AbstractModule;
import core.generators.words.IWordsGenerator;
import core.generators.words.WordsGenerator;

public class BasicModule extends AbstractModule {

  @Override
  public void configure() {
    bind(IWordsGenerator.class).to(WordsGenerator.class);
  }
}
