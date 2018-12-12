package core.commands.invoker;

import com.google.inject.Inject;
import core.commands.Arguments;
import core.commands.ExecuteResult;
import core.commands.ICommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tools.ITokenizer;

public class CommandInvoker implements ICommandInvoker {

  private final Map<String, ICommand> nameToCommand;
  private final ITokenizer tokenizer;

  @Inject
  public CommandInvoker(Set<ICommand> commands, ITokenizer tokenizer) {
    nameToCommand = new HashMap<>();

    for (var command : commands) {
      nameToCommand.put(command.getName().toLowerCase(), command);
    }

    this.tokenizer = tokenizer;
  }


  @Override
  public ExecuteResult execute(String commandLine, String callerUserName) {
    var tokens = tokenizer.getTokens(commandLine);
    var command = getCommand(tokens);
    if (command == null) {
      return new ExecuteResult("Unknown command. See /help for more");
    }
    var arguments = buildArguments(tokens, command);
    if (arguments == null) {
      return new ExecuteResult("Wrong arguments. Arguments template: " + command.getFormat(),
          null);
    }

    try {
      var result = command.execute(callerUserName, arguments);

      return result;
    } catch (Exception e) {
      return new ExecuteResult("Exception: " + e.getMessage(), null);
    }
  }

  private Arguments buildArguments(List<String> tokens, ICommand command) {
    var formatTokens = tokenizer.getTokens(command.getFormat());
    var arguments = new Arguments();

    for (var i = 1; i < formatTokens.size(); i++) {
      if (tokens.size() <= i) {
        return null;
      }

      arguments.addArgument(formatTokens.get(i).substring(1), tokens.get(i));
    }

    return arguments;
  }

  private ICommand getCommand(List<String> tokens) {
    if (tokens.size() == 0) {
      return null;
    }

    return nameToCommand.get(tokens.get(0));
  }
}
