package core.generators.words;

import core.generators.GeneratorException;
import core.generators.words.dictionaries.DictionaryMode;
import java.util.List;

public interface IWordsGenerator {
  List<String> getWords(int amount, DictionaryMode mode) throws GeneratorException;
}
