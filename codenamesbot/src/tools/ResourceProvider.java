package tools;

import java.nio.file.Path;

public class ResourceProvider {

  private static final String pathToEntryPointClass =
      ResourceProvider.class.getProtectionDomain().getCodeSource().getLocation().getPath();
  private static final String pathToResourcesDir =
      PathHelper.combine(pathToEntryPointClass, "..", "..", "resources");

  public static String combinePathToResourcesDir(String path) {
    return PathHelper.combine(pathToResourcesDir, path);
  }

  public static String combinePathToResourcesDir(Path path) {
    return combinePathToResourcesDir(path.toString());
  }
}
