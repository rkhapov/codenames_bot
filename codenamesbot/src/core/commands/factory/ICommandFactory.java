package core.commands.factory;

import core.commands.ICommand;

public interface ICommandFactory {
  ICommand getCommandByName(String name);
}
