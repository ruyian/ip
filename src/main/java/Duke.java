import duke.exception.EmptyInputException;
import duke.exception.IrregularInputException;
import duke.exception.RepeatedCompletionException;
import duke.task.Task;
import duke.task.TaskBank;

import java.io.File;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            File f = new File(TaskBank.filePath);
            if (f.exists()) {
                Chatter.load();
            } else {
                Chatter.greet();
            }
            Chatter.talk(sc);
        }
    }
}

class Chatter {
    static TaskBank taskBank = new TaskBank();

    static void printDashLine() {
        System.out.printf("____________________________________________________________%n");
    }

    static void greet() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "Its the first time I see you%n" +
                "Key in your tasks below!%n");
        printDashLine();
    }

    static void load() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "I have loaded the tasks you have keyed in the last time%n" +
                "Continue to key in your tasks below!%n");
        printDashLine();
        taskBank.loadTasks();
    }

    static void talk(Scanner sc) {
        String sentence;
        while (true) {
            try {
                sentence = sc.nextLine();
                if (sentence.isEmpty()) {
                    throw new EmptyInputException("Empty input! Try again (o|o)\n");
                } else if (sentence.equals("bye")) {
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
                } else if (sentence.startsWith("delete")) {
                    Chatter.deleteTask(sentence);
                } else if (sentence.startsWith("clear")) {
                    Chatter.clear();
                } else {
                    throw new IrregularInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                            "\nPlease try again!");
                }
            } catch (IrregularInputException e) {
                printDashLine();
                System.out.println(e.getMessage());
                printDashLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                printDashLine();
            } catch (RepeatedCompletionException e) {
                System.out.println(e.getMessage());
                printDashLine();
            } catch (EmptyInputException e) {
                printDashLine();
                System.out.println(e.getMessage());
                printDashLine();
            } catch (NumberFormatException e) {
                printDashLine();
                System.out.printf("Not a valid number. Try again%n");
                printDashLine();
            }
        }
    }

    static void add(Task newTask) {
        printDashLine();
        System.out.printf("Got it. I've added this task: %n " +
                newTask +
                "%nNow you have " + taskBank.getTaskSize() +
                " tasks in the list.%n");
        printDashLine();
        taskBank.exportTasks();
    }

    static void bye() {
        printDashLine();
        System.out.printf("Bye. Hope to see you again soon!%n");
        printDashLine();
    }

    static void list() {
        printDashLine();
        System.out.printf("Here are the tasks in your list:%n");
        taskBank.printList();
        printDashLine();
    }

    static void done(String sentence) throws RepeatedCompletionException, NumberFormatException {
        String[] words = sentence.split(" ");
        int targetIndex = Integer.parseInt(words[1]) - 1;
        Task targetTask = taskBank.searchTask(targetIndex);
        targetTask.markAsDone();
        taskBank.exportTasks();
        System.out.printf("Nice! I've marked this task as done: %n");
        System.out.println("  " + targetTask);
        printDashLine();
    }

    static void deleteTask(String sentence) {
        String[] words = sentence.split(" ");
        int targetIndex = Integer.parseInt(words[1]) - 1;
        Task deletedTask = Chatter.taskBank.removeTask(targetIndex);
        printDashLine();
        System.out.printf("Noted. I've removed this task: %n");
        System.out.println("  " + deletedTask);
        System.out.printf("Now you have " + taskBank.getTaskSize() +
                " tasks in the list.%n");
        taskBank.exportTasks();
        printDashLine();
    }

    static void clear() {
        taskBank.clear();
        taskBank.exportTasks();
        System.out.println("I have cleared all the tasks!");
        printDashLine();
    }
}

