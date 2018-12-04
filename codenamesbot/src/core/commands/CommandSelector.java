package core.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.reflections.Reflections;

public class CommandSelector {

  private static final Map<String, ICommand> stringToCommand;

  static {
    var reflections = new Reflections("core.commands");
    var commClasses = reflections.getSubTypesOf(ICommand.class);
    stringToCommand = new HashMap<>();

    for (var command : commClasses){
      try{
        var constr = command.getConstructor();
        var inst = constr.newInstance();

        stringToCommand.put(inst.getStringCommand(), inst);
      } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
        //nothing
      }
    }
  }

  public static ICommand getCommandByString(String cmd) {
    return stringToCommand.get(cmd);
  }
}
