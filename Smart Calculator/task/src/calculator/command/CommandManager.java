package calculator.command;

import calculator.command.ICommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean isLegalCommand(String exp) {
      List<String> names = commands.stream().map(ICommand::getName).collect(Collectors.toList());
      return names.indexOf(exp) != -1;
    }

    public ICommand findCommand(String expression) {
        List<String> names = commands.stream().map(ICommand::getName).collect(Collectors.toList());
        return commands.get(names.indexOf(expression));
    }
}
