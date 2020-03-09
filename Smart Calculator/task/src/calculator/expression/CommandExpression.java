package calculator.expression;

import calculator.command.CommandManager;
import calculator.command.ExitCommand;
import calculator.command.HelpCommand;
import calculator.exception.UnknownCommand;;

public class CommandExpression implements IExpression {
  public static final String PATTERN = "^/\\w+$";
  private String expression;
  private CommandManager manager;

  public CommandExpression(String expression) {
    this.expression = expression;
    createCommandManager();
  }

  @Override
  public boolean eval() {
    if(expression.equals("/help"))  {
    System.out.println(manager.findCommand(expression).getDescription());
    return true;
    }

    return false;
  }

  @Override
  public boolean matchPattern() throws UnknownCommand {
      String exp = getExpression();
    if (exp.matches(PATTERN)) {
      if (manager.isLegalCommand(exp)) return true;
      else throw new UnknownCommand();
    }

    return false;
  }

  @Override
  public String getExpression() {
    return this.expression.replaceAll(" ", "");
  }

  private void createCommandManager() {
    CommandManager manager = new CommandManager();
    HelpCommand helpCommand = new HelpCommand();
    ExitCommand exitCommand = new ExitCommand();
    manager.addCommand(helpCommand, exitCommand);
    this.manager = manager;
  }
}
