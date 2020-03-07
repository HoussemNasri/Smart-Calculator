package calculator.command;

public class ExitCommand implements ICommand{
    @Override
    public String getName() {
        return "/exit";
    }

    @Override
    public String getDescription() {
        return "Exit the program and stop accepting user input";
    }
}
