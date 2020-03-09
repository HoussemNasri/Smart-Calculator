package calculator.expression;

import calculator.exception.*;

import java.util.HashMap;

public class ExpressionFactory {

    public static IExpression build (String expression ){
        try{
            VariableExpression va = new VariableExpression(expression);
            ArithmeticExpression ar = new ArithmeticExpression(expression);
            CommandExpression co = new CommandExpression(expression);

            if(va.matchPattern()){
                return va;
            }
            if(co.matchPattern()){
                return co;
            }
            if(ar.matchPattern()){
                return ar;
            }

        }
        catch (InvalidAssignment | InvalidIdentifier | InvalidExpression | UnknownCommand | UnknownVariable e){
              System.out.println(e.getMessage());
        }

        return null;
    }
}
