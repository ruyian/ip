import duke.exception.DukeException;
import duke.exception.IrregularInputException;
import duke.exception.RepeatedCompletionException;
import duke.task.Task;
import duke.task.TaskBank;

public class Command {
    public static void perform(String sentence, Action action, Ui ui, Storage storage, TaskBank tb) throws DukeException {
        switch (action) {
        case CLEAR:
            clear(tb,storage);
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
        default:
            System.out.println("ERROR IN PARSING");
        }

    }

    private static void clear(TaskBank tb, Storage storage) {
        tb.clear();
    }

    private static Task deleteTask(String deleteStament, TaskBank tb) throws IrregularInputException {
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
        switch (action) {
        case CREATE_TODO:
            newTask = tb.addTodo(sentence);
            break;
        case CREATE_DEADLINE:
            newTask = tb.addDeadline(sentence);
            break;
        case CREATE_EVENT:
            newTask = tb.addEvent(sentence);
            break;
        default:
            newTask = null;
        }
        return newTask;
    }

    private static Task completeTask(TaskBank tb, String sentence) throws RepeatedCompletionException, NumberFormatException, IrregularInputException {
        int targetIndex = Parser.parseIndex(sentence);
        Task completedTask = tb.searchTask(targetIndex);
        completedTask.markAsDone();
        return completedTask;
    }
}
