package core.commands.invoker;

import core.commands.ExecutionResult;

public interface ICommandInvoker {
  ExecutionResult execute(String commandLine, String callerUserName, Long chatId);
}
