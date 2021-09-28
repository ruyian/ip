import duke.task.Task;
import duke.task.TaskBank;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void printDashLine() {
        System.out.printf("____________________________________________________________%n");
    }

    public void showGreeting() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "Its the first time I see you%n" +
                "Key in your tasks below!%n");
        printDashLine();
    }


    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showClearMessage() {
        System.out.println("I have cleared all the tasks!");
    }

    public void showDeleteMessage(Task deletedTask, int taskBankSize) {
        System.out.printf("Noted. I've removed this task: %n");
        System.out.println("  " + deletedTask);
        System.out.printf("Now you have " + taskBankSize +
                " tasks in the list.%n");
    }

    public void showByeMessage() {
        System.out.printf("Bye. Hope to see you again soon!%n");
    }

    public void showAllTaskMessage() {
        System.out.printf("Here are the tasks in your list:%n");
    }

    public void showTaskAddedMessage(Task addedTask, int taskBankSize) {
        System.out.printf("Got it. I've added this task: %n " +
                addedTask +
                "%nNow you have " + taskBankSize +
                " tasks in the list.%n");
    }

    public void showCompleteMessage(Task completedTask) {
        System.out.printf("Nice! I've marked this task as done: %n");
        System.out.println("  " + completedTask);
    }

    public String readInput(Scanner sc) {
        return sc.nextLine();
    }

    public static ArrayList<Task> findMatchingTask(TaskBank givenBank, String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : givenBank.getTasks()) {
            String lowercaseTaskDescription = task.getDescription().toLowerCase();
            if (lowercaseTaskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    public void showLoadMessage() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "I have loaded the tasks you have keyed in the last time%n" +
                "Continue to key in your tasks below!%n");
        printDashLine();
    }

    public void showFindMessage() {
        System.out.printf(" Here are the matching tasks in your list:%n");
    }
}
