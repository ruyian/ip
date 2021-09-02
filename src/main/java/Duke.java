import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String sentence;
            Chatter.greet();
            while (true) {
                sentence = sc.nextLine();
                if (sentence.equals("bye")) {
                    Chatter.bye();
                    break;
                } else if (sentence.equals("list")) {
                    Chatter.list();
                } else if (sentence.startsWith("done")) {
                    Chatter.done(sentence);
                } else if (sentence.startsWith("todo")) {
                    Task newTask = Chatter.taskBank.addTodo(sentence);
                    Chatter.add(sentence, newTask);
                } else if (sentence.startsWith("deadline")) {
                    Task newTask = Chatter.taskBank.addDeadline(sentence);
                    Chatter.add(sentence, newTask);
                } else if (sentence.startsWith("event")) {
                    Task newTask = Chatter.taskBank.addEvent(sentence);
                    Chatter.add(sentence, newTask);
                } else {
                    System.out.println("I'm not sure if I get what you've said.");
                }
            }
        }
    }
}

class Chatter {
    static TaskBank taskBank = new TaskBank();

    static void greet() {
        System.out.printf("____________________________________________________________%n" +
                "Hello! I'm Duke, your task manager.%n" +
                "Key in your tasks below!%n" +
                "____________________________________________________________%n");
    }

    static void add(String sentence, Task newTask) {
        System.out.printf("____________________________________________________________%n" +
                "Got it. I've added this task: %n " +
                newTask +
                "%nNow you have " + taskBank.getTaskIndex() +
                " tasks in the list.%n" +
                "____________________________________________________________%n");
    }

    static void bye() {
        System.out.printf("____________________________________________________________%n" +
                "Bye. Hope to see you again soon!%n" +
                "____________________________________________________________%n");
    }

    static void list() {
        System.out.printf("____________________________________________________________%n");
        System.out.printf("Here are the tasks in your list:%n");
        taskBank.printList();
        System.out.printf("____________________________________________________________%n");
    }

    static void done(String sentence) {
        String[] words = sentence.split(" ");
        int targetIndex = Integer.parseInt(words[1]) - 1;
        Task targetTask = taskBank.searchTask(targetIndex);
        if (targetTask == null) {
            System.out.println("Ouch! Index is out of range. Try again!");
            System.out.printf("____________________________________________________________%n");
            return;
        } else if (targetTask.getDone()) {
            System.out.println("Task is already completed");
            System.out.printf("____________________________________________________________%n");
            return;
        }
        targetTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + targetTask.getTaskType() + "]" + "[X] " + targetTask.getDescription());
        System.out.printf("____________________________________________________________%n");
    }
}

