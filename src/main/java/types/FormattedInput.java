package types;
import java.util.HashMap;

public class FormattedInput {
    public CommandEnum commandType;
    public String firstParam;
    public HashMap<String, String> params;

    public FormattedInput() {
        params = new HashMap<>();
    }
}
