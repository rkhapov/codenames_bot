package core.commands;

import com.google.inject.Inject;
import java.util.Set;
import tools.Lazy;

public class HelpCommand implements ICommand{

  private final Lazy<Set<ICommand>> commands;

  @Inject
  public HelpCommand(Lazy<Set<ICommand>> commands) {
    this.commands = commands;
  }

  @Override
  public ExecuteResult execute(String callerUserName, Arguments arguments) {
    var result = new StringBuilder();
    for (var command : commands.getValue()) {
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
