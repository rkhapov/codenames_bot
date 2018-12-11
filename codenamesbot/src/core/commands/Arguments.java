package core.commands;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

  private final Map<String, String> nameToValue;

  public Arguments() {
    nameToValue = new HashMap<>();
  }

  public Arguments(Map<String, String> nameToValue) {
    this.nameToValue = nameToValue;
  }

  public Arguments addArgument(String name, String value) {
    if (name == null || value == null) {
      throw new IllegalArgumentException();
    }

    nameToValue.put(name, value);

    return this;
  }

  public String getArgument(String name) {
    return nameToValue.get(name);
  }

  public boolean hasArgument(String name) {
    return nameToValue.containsKey(name);
  }

  public int size() {
    return nameToValue.size();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj instanceof Arguments) {
      return nameToValue.equals(((Arguments) obj).nameToValue);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return nameToValue.hashCode();
  }
}
