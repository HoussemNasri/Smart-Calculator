package calculator;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {
  private ArrayList<ICommand> commands;

  public CommandManager(ICommand... commands) {
    this.commands = new ArrayList<>();
    addCommand(commands);
  }

  public ArrayList<ICommand> getCommands () {
      return commands;
  }

  public void addCommand(ICommand ... commands) {
      this.commands.addAll(Arrays.asList(commands));
  }
}
