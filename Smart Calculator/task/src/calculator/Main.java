package calculator;

import calculator.exception.IllegalExpressionException;

import java.util.*;

public class Main {
  public static void main(String[] args) {
      Main main = new Main();
    Scanner scanner = new Scanner(System.in);

      try {
          ExpressionEvaluator evaluator = new ExpressionEvaluator("2 ---- 1 ++ 5 --- 3");
          System.out.println(evaluator.getTokens().toString());
      } catch (IllegalExpressionException e) {
          e.printStackTrace();
      }


      boolean running= true;
    do {
        running = main.readUserInput(scanner);
    } while (running);

    //user called the /exit command
    System.out.println("Bye!");
  }

  public int[] lineToInteger(String line) {

    StringTokenizer tokenizer = new StringTokenizer(line);
    int[] input = new int[tokenizer.countTokens()];

    int i = 0;
    try {
      while (tokenizer.hasMoreTokens())
          input[i++] = Integer.parseInt(tokenizer.nextToken());

    } catch (Exception e) {
      input[i] = 0;
    }

    return input;
  }

  public CommandManager createCommandManager () {
      CommandManager manager = new CommandManager();
      manager.addCommand(new HelpCommand());
      manager.addCommand(new ExitCommand());

      return manager;
  }

  public boolean readUserInput(Scanner scanner) {
    String line = scanner.nextLine();

    ICommand helpCo = new HelpCommand();
    ICommand exitCo = new ExitCommand();

    if (line.equals(exitCo.getName())) return false;

    if (line.equals(helpCo.getName())) {
      System.out.println(helpCo.getDescription());
      return true;
    }

    if (line.isEmpty()) return true;

    int[] input = lineToInteger(line);
    System.out.println(sum(input));

    return true;
  }

  public int sum(int... vars) {
    int sum = 0;
    for (int i : vars) sum += i;

    return sum;
  }
}
