import java.util.Scanner;
import tasks.ProgramData;
import types.FormattedInput;
import utils.DataSaver;
import utils.InputProcessor;
import utils.OutputFormatter;

public class ApunableBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String botName = "ApunableBot"; // A pure pineapple bot

        ProgramData.tasks = DataSaver.loadFromFile();

        OutputFormatter.outputs.add(String.format("Hello! I'm %s", botName));
        OutputFormatter.outputs.add("What can I do for you?");
        OutputFormatter.flushOutput();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                DataSaver.saveToFile(ProgramData.tasks);
                OutputFormatter.echo("Bye. Hope to see you again soon!");
                break;
            }

            FormattedInput formattedInput = InputProcessor.formatInput(input);
            if (formattedInput != null) {
                formattedInput.commandType.method.accept(formattedInput);
            }
        }

        sc.close();
    }
}
