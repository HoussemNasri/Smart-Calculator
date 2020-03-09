package calculator.expression;


import calculator.exception.*;

public interface IExpression {
    boolean eval();
    boolean matchPattern () throws InvalidAssignment, InvalidIdentifier, InvalidExpression, UnknownCommand, UnknownVariable;
    String getExpression();
}
