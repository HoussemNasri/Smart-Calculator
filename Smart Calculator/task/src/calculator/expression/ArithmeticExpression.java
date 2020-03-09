package calculator.expression;

import calculator.Main;
import calculator.MathOperator;
import calculator.exception.InvalidExpression;
import calculator.exception.UnknownVariable;

import java.util.*;

public class ArithmeticExpression implements IExpression {
  public static final String PATTERN = "^(-+)*\\w+([+-]+\\w)*$";
  private List<String> tokens;
  private Character[] legalOperators = {'-', '+'};
  private String expression;

  public ArithmeticExpression(String expression ) {
    this.expression = expression;
    this.tokens = new ArrayList<>();
  }

  @Override
  public boolean eval() {
      int result = Integer.parseInt(this.tokens.get(0));
      while (tokens.size() > 2) {
          result = simpleEval(1);
          cleanUpExpression(result, 1);
      }

      System.out.println(result);
      return true;
  }

  @Override
  public boolean matchPattern() throws InvalidExpression, UnknownVariable {
    String pattern = PATTERN;
    tokenizer();
    if(getExpression().matches(pattern))
        return true;

    return false;
  }

  @Override
  public String getExpression() {
    return this.expression.replaceAll(" ", "");
  }

  private String convertFollowingOperators(String operators) {
    char firstOp = operators.charAt(0);
    for (int i = 1; i < operators.length(); i++) {
      if (firstOp != operators.charAt(i)) return " ";
    }

    if (firstOp == '-') {
      if (operators.length() % 2 == 0) return "+";
      else return "-";
    } else return String.valueOf(firstOp);
  }

  private void tokenizer() throws InvalidExpression, UnknownVariable {
    StringTokenizer stringTokenizer = new StringTokenizer(expression);
    boolean number = true;
    int tokenTracker = 0;
    while (stringTokenizer.hasMoreTokens()) {
      String tempToken = stringTokenizer.nextToken();
      if (number) {
        if (isNumber(tempToken)) tokens.add(tempToken);
        else if(isVariable(tempToken)) tokens.add(Main.variables.get(tempToken) + "");
        else throw new InvalidExpression();

      } else {

        tempToken = convertFollowingOperators(tempToken);

        if (isOperator(tempToken)) tokens.add(tempToken);
        else throw new InvalidExpression();
      }
      number = !number;
      tokenTracker++;
    }

  }

  public void cleanUpExpression(int result, int operatorIndex) {
    tokens.add(operatorIndex - 1, result + "");
    for (int i = 0; i < 3; i++) tokens.remove(operatorIndex);
  }

  public int simpleEval(int operatorIndex) {
    int a = Integer.parseInt(tokens.get(operatorIndex - 1));
    int b = Integer.parseInt(tokens.get(operatorIndex + 1));
    char op = tokens.get(operatorIndex).charAt(0);

    return MathOperator.operate(a, b, op);
  }

  public boolean isNumber(String str) {
      if(str.charAt(0) != '+' && str.charAt(0) != '-' && !Character.isDigit(str.charAt(0)))
          return false;
    for (int i = 1; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) return false;
    }
    return true;
  }

  public boolean isVariable (String key) throws UnknownVariable {
      Integer value = Main.variables.get(key);
      if(value != null)
      return true;
      else
          throw new UnknownVariable();
  }

  public boolean isOperator(String operator) {
    if (operator.length() > 1 || operator.isEmpty()) return false;

    Arrays.sort(legalOperators);
    List<Character> listOfOperators = Arrays.asList(legalOperators);
    if (listOfOperators.indexOf(operator.charAt(0)) != -1) {
      return true;
    }
    return false;
  }
}
