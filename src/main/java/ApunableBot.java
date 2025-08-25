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
        System.out.println(
            String.format("""
            ____________________________________________________________
            Hello! I'm %s
            What can I do for you?
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________"""
            , botName)
        );

        // String input = "";
        // while(!input.equals("bye")) {
        //     input = sc.nextLine();
        //     System.out.println("____________________________________________________________");
        //     if (input.equals("bye")) {
        //         System.out.println("Bye. Hope to see you again soon!");
        //     } else {
        //         System.out.println(input);
        //     }
        //     System.out.println("____________________________________________________________");
        // }
    
    }
}
