package tools;

import java.nio.file.Path;

public class ResourceProvider {

  private final String pathToResourcesDir;

  public ResourceProvider(String pathToResourcesDir) {
    this.pathToResourcesDir = pathToResourcesDir;
  }

  public String combinePathToResourcesDir(String path) {
    return Utils.combine(pathToResourcesDir, path);
  }

  public String combinePathToResourcesDir(Path path) {
    return combinePathToResourcesDir(path.toString());
  }
}
