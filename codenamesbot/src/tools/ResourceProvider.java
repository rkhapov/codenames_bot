package tools;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceProvider {

  private static final String pathToEntryPointClass =
      ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath();
  private static final String pathToResourcesDir =
      Paths.get(pathToEntryPointClass, "..", "..", "resources").toString();

  public static String combinePathToResourcesDir(String path) {
    return Paths.get(pathToResourcesDir, path).toString();
  }

  public static String combinePathToResourcesDir(Path path) {
    return combinePathToResourcesDir(path.toString());
  }
}
