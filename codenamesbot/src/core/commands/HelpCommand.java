package core.commands;

import com.google.inject.Inject;
import java.util.Set;

public class HelpCommand implements ICommand{

  private final Set<ICommand> commands;

  @Inject
  public HelpCommand(Set<ICommand> commands) {
    this.commands = commands;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var result = new StringBuilder();
    for (var command : commands) {
      result.append(command.getFormat());
      result.append(" ");
      result.append(command.getHelp());
      result.append("\n");
    }
    return new ExecuteResult(result.toString());
  }

  @Override
  public String getName() {
    return "/help";
  }

  @Override
  public String getFormat() {
    return "/help";
  }

  @Override
  public String getHelp() {
    return "helps";
  }
}
