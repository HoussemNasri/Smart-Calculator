package calculator.exception;

public class UnknownCommand extends Exception{
    public UnknownCommand(){
        super("Unknown command");
    }
}
