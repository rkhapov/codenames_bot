package core.commands;

public interface ICommand {

  ExecuteResult execute(String callerUserName, Arguments arguments);

  String getName();

  String getFormat();

  String getHelp();
}
