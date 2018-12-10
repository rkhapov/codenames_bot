package core.commands.factory;

import core.commands.ExecuteResult;

public interface ICommandInvoker {
  ExecuteResult execute(String commandLine, String callerUserName);
}
