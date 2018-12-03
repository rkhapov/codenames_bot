package core.generators.words;

import core.generators.GeneratorException;
import core.generators.words.dictionaries.DictionaryMode;
import java.util.Collection;

public interface IWordsGenerator {
  Collection<String> getWords(int amount, DictionaryMode mode) throws GeneratorException;
}
