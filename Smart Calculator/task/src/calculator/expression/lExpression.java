package calculator.expression;


import calculator.exception.InvalidAssignment;
import calculator.exception.InvalidExpression;
import calculator.exception.InvalidIdentifier;

interface IExpression<T> {
    T eval();
    boolean matchPattern () throws InvalidAssignment, InvalidIdentifier, InvalidExpression;
    String getExpression();
}
