package utils;

import types.CommandEnum;
import exceptions.ApunableException;
import java.util.HashMap;

public class Parser {
    /**
     * Normalizes and fits the user input into {@code FormattedInput}, easier for further process. 
     * 
     * @param input user input
     * @return a {@code FormattedInput} instance consists of command type, parameter names and parameters
     */
    public static Command parse(String input) throws ApunableException {
        String[] temp = input.trim().split(" ", 2);

        String commandType = temp[0];
        CommandEnum cmd = CommandEnum.fromString(commandType);
        if (cmd == null) {
            throw new ApunableException("Invalid command");
        }

        String firstParam = "";
        HashMap<String, String> params = new HashMap<>();

        if (temp.length >= 2) {
            // split based on slash that followed by an alphabet
            temp = temp[1].trim().split("/(?=[a-zA-Z])", 2);
            firstParam = temp[0];

            if (temp.length >= 2) {
                // split based on slash that followed by an alphabet
                temp = temp[1].trim().split("/(?=[a-zA-Z])");
                for(String argString : temp) {
                    String[] paramArg = argString.split(" ", 2);
                    String paramName = paramArg[0];
                    String argument = "";
                    
                    if (paramArg.length >= 2) {
                        argument = paramArg[1];
                    }

                    params.put(paramName, argument);
                }
            }
        }

        return new Command(cmd, firstParam, params);
    }
}
