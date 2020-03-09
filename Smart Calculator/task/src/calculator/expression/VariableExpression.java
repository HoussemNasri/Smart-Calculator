package calculator.expression;

import calculator.Main;
import calculator.exception.InvalidAssignment;
import calculator.exception.InvalidIdentifier;

public class VariableExpression implements IExpression {
  public static final String PATTERN = "^[a-zA-Z]+=[0-9]+$";
  private String expression;
  private String ident;
  private Integer assign;

  public VariableExpression(String expression) {
    this.expression = expression;
  }

  @Override
  public boolean eval() {
    Main.variables.put(ident, assign);
    return true;
  }

  @Override
  public boolean matchPattern() throws InvalidAssignment, InvalidIdentifier {
    String pattern = PATTERN;
    String validIdentifier = "^[a-zA-Z]+=.*$";
    String validAssignment = "^.+=[0-9]+$";
    if (getExpression().matches(pattern)) {
      String[] splitByEqual = getExpression().split("=");
      ident = splitByEqual[0];
      assign = Integer.parseInt(splitByEqual[1]);
      return true;
    } else if (getExpression().matches(validIdentifier)) {
        String[] splitByEqual = getExpression().split("=");
        ident = splitByEqual[0];
        if(Main.variables.containsKey(splitByEqual[1]))
        assign = Main.variables.get(splitByEqual[1]);
        else
        throw new InvalidAssignment();
        return true;
    }
    else if (getExpression().matches(validAssignment)) throw new InvalidIdentifier();

    return false;
  }

  @Override
  public String getExpression() {
    return this.expression.replaceAll(" ", "");
  }
}
