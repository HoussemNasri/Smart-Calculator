package calculator;

import calculator.command.CommandManager;
import calculator.command.ExitCommand;
import calculator.command.HelpCommand;
import calculator.command.ICommand;
import calculator.exception.IllegalExpressionException;
import calculator.expression.ExpressionFactory;
import calculator.expression.IExpression;

import java.util.*;

public class Main {
    public static final HashMap<String, Integer> variables = new HashMap<>();
  public static void main(String[] args) throws IllegalExpressionException {
      Main main = new Main();
    Scanner scanner = new Scanner(System.in);


      boolean running= true;
    do {
        running = main.readUserInput(scanner);
    } while (running);

    //user called the /exit command
    System.out.println("Bye!");
  }



  public boolean readUserInput(Scanner scanner) throws IllegalExpressionException {
    String line = scanner.nextLine();
      IExpression expression = ExpressionFactory.build(line);
      if(expression != null) return expression.eval();

      return true;

  }

}
