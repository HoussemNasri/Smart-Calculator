package calculator;

public class HelpCommand implements ICommand {

    @Override
    public String getName() {
        return "/help";
    }

    @Override
    public String getDescription() {
    return "The program calculates the sum of numbers";
    }
}
