package calculator;

import calculator.exception.IllegalExpressionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ExpressionEvaluator {
  private List<String> expressionTokens;
  private String expression;
  private char[] legalOperators = {'-', '+'};

  public ExpressionEvaluator(String expression) throws IllegalExpressionException {
    this.expressionTokens = new ArrayList<>();
    this.expression = expression;
    expressionTokens.addAll(tokenizer());
  }

  public List<String> tokenizer() throws IllegalExpressionException {
    List<String> tokens = new ArrayList<>();
    StringTokenizer stringTokenizer = new StringTokenizer(expression);
    boolean number = true;
    int tokenTracker = 1;
    while (stringTokenizer.hasMoreTokens()) {
      String tempToken = stringTokenizer.nextToken();
      if (number) {
        if (isNumber(tempToken)) tokens.add(tempToken);

        else throw new IllegalExpressionException(tokenTracker);

      } else {

        tempToken = convertFollowingOperators(tempToken);

        if (isOperator(tempToken)) tokens.add(tempToken);

        else throw new IllegalExpressionException(tokenTracker);
      }
      number = !number;
      tokenTracker++;
    }

    return tokens;
  }

  public List<String> getTokens () {
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
    return 1;
  }

  public int simpleEval() {
    return -1;
  }

  public int nextOperator() {
    return 1;
  }

  public boolean isNumber(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) return false;
    }
    return true;
  }

  public boolean isOperator(String operator) {
    if (operator.length() > 1 || operator.isEmpty()) return false;

    Arrays.sort(legalOperators);

    if (Arrays.binarySearch(legalOperators, operator.charAt(0)) != -1) return true;
    return false;
  }
}
