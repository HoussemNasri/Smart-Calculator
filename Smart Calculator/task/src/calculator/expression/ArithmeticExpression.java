package calculator.expression;

import calculator.exception.InvalidExpression;

public class ArithmeticExpression implements IExpression<Integer> {

    private String expression;

    public ArithmeticExpression (String expression) {
        this.expression = expression;
    }
    @Override
    public Integer eval() {
        return null;
    }

    @Override
    public boolean matchPattern() throws InvalidExpression {
        String pattern = "^\\w+([+-]\\w)*$";
        if(!getExpression().matches(pattern))
            throw new InvalidExpression();
        return getExpression().matches(pattern);
    }

    @Override
    public String getExpression() {
        return this.expression.replaceAll(" " , "");
    }
}
