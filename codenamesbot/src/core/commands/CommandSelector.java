package core.commands;

public class CommandSelector {

  public static ICommand getCommandByString(String cmd) {
    if (cmd.equals("/start"))
      return new StartNewGameCommand();

    if (cmd.equals("/get"))
      return new GetCaptainPictureCommand();

    return null;
  }

}
