package tools;

import java.io.File;

public class PathHelper {

  public static String combine(String... args) {
    return String.join(File.separator, args);
  }
}
