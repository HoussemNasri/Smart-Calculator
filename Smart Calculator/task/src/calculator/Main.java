package calculator;

import calculator.command.CommandManager;
import calculator.command.ExitCommand;
import calculator.command.HelpCommand;
import calculator.command.ICommand;
import calculator.exception.InvalidExpression;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws InvalidExpression {
      Main main = new Main();
    Scanner scanner = new Scanner(System.in);


      boolean running= true;
    do {
        running = main.readUserInput(scanner);
    } while (running);

    //user called the /exit command
    System.out.println("Bye!");
  }


  public CommandManager createCommandManager () {
      CommandManager manager = new CommandManager();
      manager.addCommand(new HelpCommand());
      manager.addCommand(new ExitCommand());
      String pattern = "\\w";
      Pattern p = Pattern.compile(pattern);

      return manager;
  }

  public boolean readUserInput(Scanner scanner) throws InvalidExpression {
    String line = scanner.nextLine();

    if (line.isEmpty()) return true;

    if(line.charAt(0) == '/')
        return readCommand(line);
    else
        return readExpression(line);

  }

  public boolean readCommand (String line) {
      ICommand helpCo = new HelpCommand();
      ICommand exitCo = new ExitCommand();

      if (line.equals(exitCo.getName())) return false;

      if (line.equals(helpCo.getName())) {
          System.out.println(helpCo.getDescription());
          return true;
      }
      System.out.println("Unknown command");
      return true;
  }

  public boolean readExpression(String line) {
      ExpressionEvaluator evaluator = null;
      try{
          evaluator = new ExpressionEvaluator(line);
      }
      catch (Exception e) {
          System.out.println("Invalid expression");
          return true;
      }

      System.out.println(evaluator.eval());

      return true;
  }


}
