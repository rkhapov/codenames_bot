package core.commands;

public class StartNewGameCommand implements ICommand {

  @Override
  public CommandResult execute() {
    return new CommandResult("lol", null);
  }

  @Override
  public String getStringCommand() {
    return "/start";
  }
}
