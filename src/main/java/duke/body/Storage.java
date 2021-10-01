package duke.body;

import duke.exception.DukeException;
import duke.exception.RepeatedCompletionException;
import duke.task.Task;
import duke.task.TaskBank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;
    private final String SEPARATOR_WITH_SPACE = " | ";
    private final String SEPARATOR = "|";

    Storage(String filePath, TaskBank tb, Ui ui) {
        this.filePath = filePath;
        file = new File(filePath);
        if (file.exists()) {
            loadTasks(tb);
            ui.showLoadMessage();
        } else {
            File directoryPath = new File(filePath);
            directoryPath.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            ui.showGreeting();
        }
    }

    /**
     * Exports the content in the TaskBank to duke.txt
     *
     * @param tb - the TaskBank which tasks are exported from
     */
    public void exportTasks(TaskBank tb) {
        StringBuffer taskTextString = new StringBuffer();
        for (Task task : tb.getTasks()) {
            taskTextString.append(task.getTaskType());
            taskTextString.append(SEPARATOR_WITH_SPACE);
            taskTextString.append(task.getDone() ? "1" : "0");
            taskTextString.append(SEPARATOR_WITH_SPACE);
            taskTextString.append(task.describeInFile());
            taskTextString.append("\r\n");
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskTextString.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads taks from file to target TaskBank
     *
     * @param tb TaskBank which tasks are loaded to
     */
    public void loadTasks(TaskBank tb) {
        File f = new File(this.filePath);
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                loadTaskLine(sc.nextLine(), tb);
            }
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt is not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (RepeatedCompletionException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads a specific line in the text file, and add the parsed Task to the given taskbank
     *
     * @param taskLine - a specific line of String of the text file
     * @param tb       - the given taskbank where tasks are to be added
     * @throws IOException if input or outputs has error
     */
    private void loadTaskLine(String taskLine, TaskBank tb) throws IOException, RepeatedCompletionException {
        String taskTypeString = taskLine.substring(0, 1);
        int firstDivisor = taskLine.indexOf(SEPARATOR, 1);
        int secondDivisor = taskLine.indexOf(SEPARATOR, firstDivisor + 1);
        int thirdDivisor = taskLine.indexOf(SEPARATOR, secondDivisor + 1);
        String taskCompletionStatus = taskLine.substring(firstDivisor + 2, secondDivisor).trim();
        Task newTask;
        if (taskTypeString.equals("T")) {
            String todoInput = taskLine.substring(secondDivisor + 1).trim();
            newTask = tb.addTodo(todoInput);
        } else if (taskTypeString.equals("D")) {
            String deadLineInput = taskLine.substring(secondDivisor + 1).trim();
            newTask = tb.addDeadline(deadLineInput.replace("| ", "/"));
        } else if (taskTypeString.equals("E")) {
            String eventLineInput = taskLine.substring(secondDivisor + 1).trim();
            newTask = tb.addEvent(eventLineInput.replace("| ", "/"));
        } else {
            throw new IOException("Wrong type from data loader. File is corrupted");
        }

        if (taskCompletionStatus.equals("1")) {
            try {
                newTask.markAsDone();
            } catch (RepeatedCompletionException e) {
                throw new RepeatedCompletionException("Repeated completion on loading");
            }
        }
    }
}
