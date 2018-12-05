package core.generators.words.dictionaries;

import core.Paths;
import core.generators.GeneratorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.ResourceProvider;

public class StandardDictionary implements IDictionary {

  private List<String> words;

  public StandardDictionary() {
    words = null;
  }


  @Override
  public List<String> getWords() throws GeneratorException {
    if (words == null) {
      fill();
    }

    return words;
  }

  @Override
  public DictionaryMode getMode() {
    return DictionaryMode.Standard;
  }

  private void fill() throws GeneratorException {
    var wordsFile = new File(
        ResourceProvider.combinePathToResourcesDir(Paths.StandardDictionaryPath));

    try(var scanner = new Scanner(wordsFile)) {
      words = new ArrayList<>();

      while (scanner.hasNext()) {
        words.add(scanner.next());
      }
    } catch (FileNotFoundException e) {
      throw new GeneratorException("Cant load resource file");
    }
  }
}
