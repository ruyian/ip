import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String sentence;
            Chat.greet();
            while (true) {
                sentence = sc.nextLine();
                if (sentence.equals("bye")) {
                    Chat.bye();
                    break;
                } else if (sentence.equals("list")) {
                    Chat.list();
                } else {
                    Chat.parrot(sentence);
                }
            }
        }
    }
}

class Chat {
    static String[] texts = new String[100];
    static int textIndex = 0;

    static void greet() {
        System.out.printf("____________________________________________________________%n" +
                "Hello! I'm Duke, the parrot.%n" +
                "What can I do for you?%n" +
                "____________________________________________________________%n");
    }

    static void parrot(String sentence) {
        System.out.printf("____________________________________________________________%nadded: " +
                sentence +
                "%n____________________________________________________________%n");
        texts[textIndex++] = sentence;

    }

    static void bye() {
        System.out.printf("____________________________________________________________%n" +
                "Bye. Hope to see you again soon!%n" +
                "____________________________________________________________%n");
    }

    static void list() {
        int i;
        System.out.printf("____________________________________________________________%n");
        for (i = 0; i < textIndex; i++) {
            System.out.println((i + 1) + ". " + texts[i]);
        }
        System.out.printf("____________________________________________________________%n");
    }
}
