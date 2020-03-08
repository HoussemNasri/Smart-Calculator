package calculator.expression;

import calculator.exception.InvalidAssignment;
import calculator.exception.InvalidIdentifier;

public class VariableExpression implements IExpression<Integer>{
    private String expression;

    public VariableExpression (String expression) {
        this.expression = expression;
    }
    @Override
    public Integer eval() {
        return null;
    }

    @Override
    public boolean matchPattern() throws InvalidAssignment, InvalidIdentifier {
        String pattern  = "^[a-zA-Z]+=[0-9]+$";
        String validIdentifier = "^[a-zA-Z]+=.*$";
        String validAssignment = "^.+=[0-9]+$";
        if(getExpression().matches(pattern))
            return true;

        else if(getExpression().matches(validIdentifier))
            throw new InvalidAssignment();

        else if (getExpression().matches(validAssignment))
            throw new InvalidIdentifier();

        return false;
    }

    @Override
    public String getExpression() {
        return this.expression.replaceAll(" " , "");
    }
}
