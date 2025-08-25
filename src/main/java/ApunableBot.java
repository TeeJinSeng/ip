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

        System.out.println(
            String.format("""
            ____________________________________________________________
            Hello! I'm %s
            What can I do for you?
            ____________________________________________________________"""
            , botName)
        );

        String input = "";
        while(!input.equals("bye")) {
            System.out.print("Your query:\n");
            input = sc.nextLine();

            System.out.print("\nReply from bot: \n");
            System.out.println(horizontalLine);
            System.out.print("\t");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(input);
            }

            System.out.println(horizontalLine);
        }
    }
}
