package core.commands;

public interface ICommand {
  CommandResult execute();
  String getStringCommand();
}
