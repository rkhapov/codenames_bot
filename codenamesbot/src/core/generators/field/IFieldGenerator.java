package core.generators.field;

import core.generators.GeneratorException;
import core.primitives.Field;

public interface IFieldGenerator {
  Field generate() throws GeneratorException;
}
