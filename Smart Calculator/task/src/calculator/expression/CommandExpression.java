package calculator.expression;
;

public class CommandExpression implements IExpression<String> {
  private String expression;


  public CommandExpression(String expression) {
      this.expression = expression;
  }

  @Override
  public String eval() {
    return null;
  }

  @Override
  public boolean matchPattern() {
    String pattern = "^/\\w$";

    return getExpression().matches(pattern);
  }

  @Override
  public String getExpression() {
    return this.expression.replaceAll(" " , "");
  }
}
