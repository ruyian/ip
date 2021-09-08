import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Chatter.greet();
            Chatter.talk(sc);
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

    static void talk(Scanner sc) {
        String sentence;
        while (true) {
            try {
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
                    Chatter.add(newTask);
                } else if (sentence.startsWith("deadline")) {
                    Task newTask = Chatter.taskBank.addDeadline(sentence);
                    Chatter.add(newTask);
                } else if (sentence.startsWith("event")) {
                    Task newTask = Chatter.taskBank.addEvent(sentence);
                    Chatter.add(newTask);
                } else {
                    throw new IrregularInputException();
                }
            } catch (IrregularInputException e) {
                System.out.printf("____________________________________________________________%n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(%n" +
                        "Please try again!%n" +
                        "____________________________________________________________%n");
            } catch (IndexOutOfBoundsException e) {
                System.out.printf("Ouch! Index is out of range. Try again!%n");
                System.out.printf("____________________________________________________________%n");
            } catch (RepeatedCompletionException e) {
                System.out.println("Task is already completed");
                System.out.printf("____________________________________________________________%n");
            }
        }
    }

    static void add(Task newTask) {
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

    static void done(String sentence) throws IndexOutOfBoundsException, RepeatedCompletionException {
        String[] words = sentence.split(" ");
        int targetIndex = Integer.parseInt(words[1]) - 1;
        Task targetTask = taskBank.searchTask(targetIndex);
        if (targetTask == null) {
            throw new IndexOutOfBoundsException();
        } else if (targetTask.getDone()) {
            throw new RepeatedCompletionException();
        }
        targetTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + targetTask.getTaskType() + "]" + "[X] " + targetTask.getDescription());
        System.out.printf("____________________________________________________________%n");
    }
}

