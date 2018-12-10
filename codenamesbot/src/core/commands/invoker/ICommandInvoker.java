package core.commands.invoker;

import core.commands.ExecuteResult;

public interface ICommandInvoker {
  ExecuteResult execute(String commandLine, String callerUserName);
}
