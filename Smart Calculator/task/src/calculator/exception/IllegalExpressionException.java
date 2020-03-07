package calculator.exception;

public class IllegalExpressionException extends Exception {

    public IllegalExpressionException(int i){
        super("Unexpected character at column " + i);
    }
}
