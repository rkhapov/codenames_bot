package core.commands;

public interface ICommand {

  ExecutionResult execute(String callerUserName, Arguments arguments, Long chatId);

  String getName();

  String getFormat();

  String getHelp();
}
