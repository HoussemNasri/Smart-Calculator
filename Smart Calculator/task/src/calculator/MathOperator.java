package calculator;

public class MathOperator {

    public static int operate (int a , int b , char op) {
        if(op == '+')
            return a + b;
        if(op == '-')
            return a - b;

        throw new ArithmeticException("Something wrong with the arguments");
    }


}