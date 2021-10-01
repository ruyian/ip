package duke.body;

import duke.exception.DukeException;
import duke.exception.DukeIndexOutOfBoundException;
import duke.exception.IllegalInputException;
import duke.exception.RepeatedCompletionException;
import duke.task.Task;
import duke.task.TaskBank;

import java.util.ArrayList;


public class Command {
    /**
     * Perform action from users
     *
     * @param sentence the raw user input
     * @param action   the actino parsed from user input by Parser
     * @param ui       the UI from the Duke
     * @param storage  the storage handler of duke.txt
     * @param tb       TaskBank of all current tasks
     * @throws DukeException if unable to find tasks by an invalid index or wrong Action is keyyed in
     */
    public static void perform(String sentence, Action action, Ui ui, Storage storage, TaskBank tb) throws DukeException {
        switch (action) {
        case CLEAR:
            clear(tb);
            ui.showClearMessage();
            storage.exportTasks(tb);
            break;
        case DELETE:
            Task deletedTask = deleteTask(sentence, tb);
            ui.showDeleteMessage(deletedTask, tb.getTaskSize());
            storage.exportTasks(tb);
            break;
        case BYE:
            bye();
            ui.showByeMessage();
            break;
        case LIST:
            ui.showAllTaskMessage();
            listAllTask(tb);
            break;
        case CREATE_TODO:
        case CREATE_DEADLINE:
        case CREATE_EVENT:
            Task createdTask = createTask(action, tb, sentence);
            ui.showTaskAddedMessage(createdTask, tb.getTaskSize());
            storage.exportTasks(tb);
            break;
        case MARK_AS_DONE:
            Task completedTask = completeTask(tb, sentence);
            ui.showCompleteMessage(completedTask);
            storage.exportTasks(tb);
            break;
        case FIND:
            TaskBank matchedTaskBank = findTask(sentence, tb);
            ui.showFindMessage();
            listAllTask(matchedTaskBank);
            break;
        default:
            throw new DukeException("Error in parsing command");
        }
    }

    /**
     * Remove all tasks from the taskbank
     *
     * @param tb taskbank to be cleared
     */
    private static void clear(TaskBank tb) {
        tb.clear();
    }

    private static Task deleteTask(String deleteStament, TaskBank tb) throws DukeIndexOutOfBoundException, IllegalInputException {
        int targetIndex = Parser.parseIndex(deleteStament);
        Task deletedTask = tb.removeTask(targetIndex);
        return deletedTask;
    }

    private static void bye() {
        Duke.terminateDuke();
    }

    private static void listAllTask(TaskBank tb) {
        tb.printList();
    }

    private static Task createTask(Action action, TaskBank tb, String sentence) {
        Task newTask;
        String description = Parser.parseDescription(sentence);
        switch (action) {
        case CREATE_TODO:
            newTask = tb.addTodo(description);
            break;
        case CREATE_DEADLINE:
            newTask = tb.addDeadline(description);
            break;
        case CREATE_EVENT:
            newTask = tb.addEvent(description);
            break;
        default:
            newTask = null;
        }
        return newTask;
    }

    private static TaskBank findTask(String input, TaskBank givenTaskBank) throws IllegalInputException {
        String keywordInput = Parser.parseKeyWord(input);
        ArrayList<Task> matchingTasks = TaskBank.findMatchingTask(givenTaskBank, keywordInput);
        if (matchingTasks.isEmpty()) {
            return null;
        } else {
            return new TaskBank(matchingTasks);
        }
    }

    private static Task completeTask(TaskBank tb, String sentence) throws RepeatedCompletionException, NumberFormatException, DukeIndexOutOfBoundException, IllegalInputException {
        int targetIndex = Parser.parseIndex(sentence);
        Task completedTask = tb.searchTask(targetIndex);
        completedTask.markAsDone();
        return completedTask;
    }
}
