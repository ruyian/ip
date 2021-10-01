package duke.body;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    /**
     * Prints a dash line to separate line outputs
     */
    public void printDashLine() {
        System.out.printf("____________________________________________________________%n");
    }

    /**
     * Prints out the message to greet the user when the program
     * is run for the first time
     */
    public void showGreeting() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "Its the first time I see you%n" +
                "Key in your tasks below!%n");
        printDashLine();
    }


    /**
     * Prints out the message of caught exception
     *
     * @param e the caught exception
     */
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

    /**
     * Prints out the message when the user ends the program execution
     */
    public void showByeMessage() {
        System.out.printf("Bye. Hope to see you again soon!%n");
    }

    /**
     * Prints out the message that
     * all tasks stored are printed out
     */
    public void showAllTaskMessage() {
        System.out.printf("Here are the tasks in your list:%n");
    }

    /**
     * Prints out the message that
     * a task has successfully been added
     *
     * @param addedTask the added task
     * @param taskBankSize the taskbank size after addition
     */
    public void showTaskAddedMessage(Task addedTask, int taskBankSize) {
        System.out.printf("Got it. I've added this task: %n " +
                addedTask +
                "%nNow you have " + taskBankSize +
                " tasks in the list.%n");
    }

    /**
     * Prints out the message that the task has
     * been marked as done
     *
     * @param completedTask the task that has been marked as done
     */
    public void showCompleteMessage(Task completedTask) {
        System.out.printf("Nice! I've marked this task as done: %n");
        System.out.println("  " + completedTask);
    }

    /**
     * Reads user's input line
     *
     * @param sc A scanner object of System.in
     * @return the read raw input
     */
    public String readInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints out the message that the tasks have been loaded
     * from the duke.txt file when the user initialize the program
     */
    public void showLoadMessage() {
        printDashLine();
        System.out.printf("Hello! I'm Duke, your task manager.%n" +
                "I have loaded the tasks you have keyed in the last time%n" +
                "Continue to key in your tasks below!%n");
        printDashLine();
    }

    /**
     * Prints the message that below tasks are
     * all the matching tasks
     */
    public void showFindMessage() {
        System.out.printf(" Here are the matching tasks in your list:%n");
    }
}
