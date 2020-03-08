package calculator;


import calculator.exception.InvalidExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ExpressionEvaluator {
  private List<String> expressionTokens;
  private String expression;
  private Character[] legalOperators = {'-', '+'};

  public ExpressionEvaluator(String expression) throws InvalidExpression {
    this.expressionTokens = new ArrayList<>();
    this.expression = expression;
    expressionTokens.addAll(tokenizer());
  }

  public List<String> tokenizer() throws InvalidExpression {
    List<String> tokens = new ArrayList<>();
    StringTokenizer stringTokenizer = new StringTokenizer(expression);
    boolean number = true;
    int tokenTracker = 1;
    while (stringTokenizer.hasMoreTokens()) {
      String tempToken = stringTokenizer.nextToken();
      if (number) {
        if (isNumber(tempToken)) tokens.add(tempToken);
        else throw new InvalidExpression();

      } else {

        tempToken = convertFollowingOperators(tempToken);

        if (isOperator(tempToken)) tokens.add(tempToken);
        else throw new InvalidExpression();
      }
      number = !number;
      tokenTracker++;
    }

    return tokens;
  }

  public List<String> getTokens() {
    return expressionTokens;
  }

  public String convertFollowingOperators(String operators) {
    char firstOp = operators.charAt(0);
    for (int i = 1; i < operators.length(); i++) {
      if (firstOp != operators.charAt(i)) return " ";
    }

    if (firstOp == '-') {
      if (operators.length() % 2 == 0) return "+";
      else return "-";
    } else return String.valueOf(firstOp);
  }

  public int eval() {
    int result = Integer.parseInt(expressionTokens.get(0));
    while (expressionTokens.size() > 2) {
      result = simpleEval(1);
      cleanUpExpression(result, 1);
    }

    return result;
  }

  public void cleanUpExpression(int result, int operatorIndex) {
    expressionTokens.add(operatorIndex - 1, result + "");
    for (int i = 0; i < 3; i++) expressionTokens.remove(operatorIndex);
  }

  public int simpleEval(int operatorIndex) {
    int a = Integer.parseInt(expressionTokens.get(operatorIndex - 1));
    int b = Integer.parseInt(expressionTokens.get(operatorIndex + 1));
    char op = expressionTokens.get(operatorIndex).charAt(0);

    return MathOperator.operate(a, b, op);
  }

  public boolean isNumber(String str) {
    for (int i = 1; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) return false;
    }
    return true;
  }

  public boolean isOperator(String operator) {
    if (operator.length() > 1 || operator.isEmpty()) return false;

    Arrays.sort(legalOperators);
    List<Character> listOfOperators = Arrays.asList(legalOperators);
    if (listOfOperators.indexOf(operator.charAt(0)) != -1) {return true;}
    return false;
  }
}
