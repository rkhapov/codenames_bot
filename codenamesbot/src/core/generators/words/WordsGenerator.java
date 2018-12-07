package core.generators.words;

import com.google.inject.Inject;
import core.generators.GeneratorException;
import core.generators.words.dictionaries.DictionaryMode;
import core.generators.words.dictionaries.IDictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tools.Utils;

public class WordsGenerator implements IWordsGenerator {

  private final Map<DictionaryMode, IDictionary> modeToDictionary;

  @Inject
  public WordsGenerator(Set<IDictionary> dictionaries) {
    modeToDictionary = new HashMap<>();

    for (var dict : dictionaries) {
      modeToDictionary.put(dict.getMode(), dict);
    }
  }

  @Override
  public List<String> getWords(int amount, DictionaryMode mode) throws GeneratorException {
    var dictionary = modeToDictionary.get(mode);

    if (dictionary == null) {
      throw new GeneratorException(
          String.format("No any dictionary for mode %s are presented", mode.toString()));
    }

    return Utils.getRandomElementsFromList(dictionary.getWords(), amount);
  }
}
