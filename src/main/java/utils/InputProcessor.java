package utils;

import types.CommandEnum;
import types.FormattedInput;

public class InputProcessor {
    /**
     * Normalizes and fits the user input into {@code FormattedInput}, easier for further process. 
     * 
     * @param input user input
     * @return a {@code FormattedInput} instance consists of command type, parameter names and parameters
     */
    public static FormattedInput formatInput(String input) {
        FormattedInput formattedInput = new FormattedInput();

        String[] inputs = input.trim().split(" ", 2);
        String command = inputs[0];

        CommandEnum cmd = CommandEnum.fromString(command);
        if (cmd == null) {
            OutputFormatter.echo(input);
            return null;
        }
        formattedInput.commandType = cmd;

        if (inputs.length == 1) {
            return formattedInput;
        }

        String[] params = inputs[1].split("/");

        formattedInput.firstParam = params[0].trim();

        if (formattedInput.firstParam.isEmpty()) {
            OutputFormatter.echo("invalid input");
            return null;
        }

        for (int i = 1; i < params.length; i++) {
            String[] paramArg = params[i].split(" ", 2);

            if (paramArg.length < 2 || paramArg[0].trim().isEmpty() || paramArg[1].trim().isEmpty()) {
                OutputFormatter.echo("invalid input");
                return null;
            }

            formattedInput.params.put(paramArg[0], paramArg[1]);
        }

        return formattedInput;
    }
}
