package core.commands;

public class GetCaptainPictureCommand implements ICommand {

  @Override
  public CommandResult execute() {
    return new CommandResult("hui", null);
  }

  @Override
  public String getStringCommand() {
    return "/getc";
  }
}
