package tools;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class Lazy<T> {

  private T value;
  private Provider<T> provider;

  @Inject
  public Lazy(Provider<T> provider) {
    this.provider = provider;
    value = null;
  }

  public T getValue() {
    if (value == null) {
      value = provider.get();
    }

    return value;
  }
}
