package duke.body;

import duke.exception.DukeException;
import duke.task.TaskBank;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskBank taskBank;
    private Ui ui;
    private static boolean isTerminating;
    private static final String TEXT_FILE_PATH= "./data/duke.txt";

    /**
     * Instantiates Storage, TaskBank and Ui  of the programme
     *
     * @param filePath the filePath which duke.txt is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        taskBank = new TaskBank();
        storage = new Storage(filePath, taskBank, ui);
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            String fullCommand;
            while (!isTerminating) {
                fullCommand = ui.readInput(sc);
                ui.printDashLine();
                try {
                    Action action = Parser.parseCommand(fullCommand);
                    Command.perform(fullCommand, action, ui, storage, taskBank);
                } catch (DukeException e) {
                    ui.showErrorMessage(e);
                }
                ui.printDashLine();
            }
        }
    }

    public static void main(String[] args) {
        isTerminating = false;
        new Duke(TEXT_FILE_PATH).run();
    }

    /**
     * Changes the variable isTerminating to true
     * Used to end the while loop that continuously read user input
     */
    public static void terminateDuke() {
        isTerminating = true;
    }
}



