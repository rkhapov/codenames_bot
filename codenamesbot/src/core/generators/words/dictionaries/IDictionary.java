package core.generators.words.dictionaries;

import core.generators.GeneratorException;
import java.util.List;

public interface IDictionary {
  List<String> getWords() throws GeneratorException;
  DictionaryMode getMode();
}
