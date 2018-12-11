package tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import core.commands.Arguments;
import core.commands.ExecuteResult;
import core.commands.GetStateCommand;
import core.commands.HelpCommand;
import core.commands.ICommand;
import core.commands.JoinCommand;
import core.commands.OpenCardCommand;
import core.commands.StartNewGameCommand;
import core.commands.invoker.CommandInvoker;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import tools.ITokenizer;

public class CommandInvokerUnitTests {

  private CommandInvoker invoker;
  private StartNewGameCommand startNewGameCommand;
  private HelpCommand helpCommand;
  private JoinCommand joinCommand;
  private OpenCardCommand openCardCommand;
  private GetStateCommand getStateCommand;
  private ITokenizer tokenizer;

  @BeforeEach
  public void init() {
    startNewGameCommand = createCommand(StartNewGameCommand.class, "/start", "");
    helpCommand = createCommand(HelpCommand.class, "/help", "");
    joinCommand = createCommand(JoinCommand.class, "/join", "/join $id $rank");
    openCardCommand = createCommand(OpenCardCommand.class, "/open", "/open $word");
    getStateCommand = createCommand(GetStateCommand.class, "/state", "/state");

    tokenizer = mock(ITokenizer.class);

    when(tokenizer.getTokens(anyString())).thenAnswer((Answer<List<String>>) invocationOnMock -> {
      var line = (String) invocationOnMock.getArguments()[0];

      return Arrays.asList(line.split(" "));
    });

    invoker = new CommandInvoker(
        Set.of(startNewGameCommand, helpCommand, joinCommand, openCardCommand, getStateCommand),
        tokenizer);
  }

  private <T extends ICommand> T createCommand(Class<? extends T> clazz, String name, String format) {
    var m = mock(clazz);
    when(m.getName()).thenReturn(name);
    when(m.getFormat()).thenReturn(format);
    when(m.execute(any(), any())).thenReturn(new ExecuteResult());

    return m;
  }

  @Test
  public void invoke_withStartString_shouldCallStartCommand() {
    invoker.execute("/start", "user");

    verify(startNewGameCommand, times(1)).execute(any(), any());
  }

  @Test
  public void invoke_withHelpCommand_shouldCallHelpCommand() {
    invoker.execute("/help", "user");

    verify(helpCommand, times(1)).execute(any(), any());
  }

  @Test
  public void invoke_withJoinCommand_shouldCallJoinCommand() {
    var expectedArguments =
        new Arguments()
            .addArgument("id", "lal")
            .addArgument("rank", "klal");

    invoker.execute("/join lal klal", "user");

    verify(joinCommand, times(1)).execute(any(), eq(expectedArguments));
  }

  @Test
  public void invoke_withOpenCommand_shouldCallOpenCommand() {
    var expectedArguments = new Arguments()
        .addArgument("word", "myword");

    invoker.execute("/open myword", "user");

    verify(openCardCommand, times(1)).execute(any(), eq(expectedArguments));
  }

  @Test
  public void invoke_withStateCommand_shouldCallStateCommand() {
    invoker.execute("/state", "user");

    verify(getStateCommand, times(1)).execute(any(), any());
  }
}
