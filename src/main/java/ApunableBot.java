import java.util.*;

public class ApunableBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        
        String botName = "ApunableBot";
        String horizontalLine = "____________________________________________________________";

        System.out.printf("""
        ____________________________________________________________
        Hello! I'm %s
        What can I do for you?
        ____________________________________________________________
        """
        , botName);

        ArrayList<String> tasks = new ArrayList<>();

        String input = "";
        while(!input.equals("bye")) {
            System.out.print("Your query:\n");
            input = sc.nextLine();

            System.out.print("\nReply from bot: \n");
            System.out.println(horizontalLine);

            switch (input) {
                case "bye" -> System.out.println("\tBye. Hope to see you again soon!");
                case "list" -> {
                    for(int i = 0; i < tasks.size(); i ++) {
                        System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
                    }
                }
                default -> {
                    tasks.add(input);
                    System.out.printf("\tadded: %s\n", input);
                }
            }

            System.out.println(horizontalLine);
        }
    }
}
