package core.generators.words;

import core.generators.GeneratorException;
import core.generators.words.dictionaries.DictionaryMode;
import core.generators.words.dictionaries.IDictionary;
import core.tools.RandomHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.reflections.Reflections;

public class WordsGenerator implements IWordsGenerator {

  private static final Map<DictionaryMode, IDictionary> modeToDictionary;

  static {
    var reflections = new Reflections("core.generators.words.dictionaries");
    var dictClasses = reflections.getSubTypesOf(IDictionary.class);
    modeToDictionary = new HashMap<>();

    for (var dictClass : dictClasses) {
      try {
        var ctor = dictClass.getConstructor();
        var instance = ctor.newInstance();

        modeToDictionary.put(instance.getMode(), instance);
      } catch (Exception e) {
        //nothing to do: broken dictionary
      }
    }
  }

  @Override
  public List<String> getWords(int amount, DictionaryMode mode) throws GeneratorException {
    var dictionary = modeToDictionary.get(mode);

    if (dictionary == null) {
      throw new GeneratorException(
          String.format("No any dictionary for mode %s are presented", mode.toString()));
    }

    return RandomHelper.getRandomElementsFromList(dictionary.getWords(), amount);
  }
}
