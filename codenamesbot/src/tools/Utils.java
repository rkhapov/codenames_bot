package tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

  private static final Random random = new Random();

  public static <T> List<T> getRandomElementsFromList(List<T> source, int amount) {
    if (amount < 0 || amount > source.size()) {
      var msg = "Amount of random elements should be in range 0..source.size()";
      throw new IllegalArgumentException(msg);
    }

    var indexes = getRandomIndexes(source.size());
    var result = new ArrayList<T>();

    for (var i = 0; i < amount; i++) {
      result.add(source.get(indexes.get(i)));
    }

    return result;
  }

  public static List<Integer> getRandomIndexes(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount cant be negative or zero");
    }

    var indexes = IntStream.range(0, amount).boxed().collect(Collectors.toList());

    for (var i = 0; i < indexes.size(); i++) {
      var target = random.nextInt(indexes.size());
      var t = indexes.get(target);
      indexes.set(target, indexes.get(i));
      indexes.set(i, t);
    }

    return indexes;
  }

  public static <T> T getRandomElement(List<T> elements) {
    if (elements.size() == 0) {
      throw new IllegalArgumentException("No any elements in list");
    }

    return elements.get(random.nextInt(elements.size()));
  }

  public static int getRandomInt(int min, int max) {
    return random.nextInt(max - min + 1) + min;
  }

  public static String combine(String... args) {
    return String.join(File.separator, args);
  }
}
