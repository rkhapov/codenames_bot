package core.commands.factory;

import com.google.inject.Inject;
import core.commands.ICommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandFactory implements ICommandFactory {

  private final Map<String, ICommand> nameToCommand;

  @Inject
  public CommandFactory(Set<ICommand> commands) {
    nameToCommand = new HashMap<>();

    for (var command : commands) {
      nameToCommand.put(command.getName().toLowerCase(), command);
    }
  }


  @Override
  public ICommand getCommandByName(String name) {
    return nameToCommand.get(name.toLowerCase());
  }
}
