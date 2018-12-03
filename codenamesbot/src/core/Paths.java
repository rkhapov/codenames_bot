package core;

public class Paths {

  public static final String DictionariesDirPath = "dictionaries";
  public static final String StandardDictionaryPath =
      java.nio.file.Paths.get(DictionariesDirPath, "standard.txt").toString();
}
