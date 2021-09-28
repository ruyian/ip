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
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
            ui.showGreeting();
        }
    }

    public void exportTasks(TaskBank tb) {
        StringBuffer taskTextString = new StringBuffer();
        for (Task task : tb.getTasks()) {
            taskTextString.append(task.getTaskType());
            taskTextString.append(" | ");
            taskTextString.append(task.getDone() ? "1" : "0");
            taskTextString.append(" | ");
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
        }
    }

    private void loadTaskLine(String taskLine, TaskBank tb) throws IOException {
        String taskTypeString = taskLine.substring(0, 1);
        int firstDivisor = taskLine.indexOf("|", 1);
        int secondDivisor = taskLine.indexOf("|", firstDivisor + 1);
        int thirdDivisor = taskLine.indexOf("|", secondDivisor + 1);
        if (thirdDivisor == -1) { // todo type
            tb.addTodo(taskLine.substring(secondDivisor + 1).trim());
        } else {
            if (taskTypeString.equals("D")) {
                String deadLineInput = taskLine.substring(secondDivisor + 1).trim();
                String taskCompletionStatus = taskLine.substring(firstDivisor + 2, secondDivisor).trim();
                Task newTask = tb.addDeadline(deadLineInput.replace("| ", "/"));
                if (taskCompletionStatus.equals("1")) {
                    try {
                        newTask.markAsDone();
                    } catch (RepeatedCompletionException e) {
                        // This is left blank intentinally
                        // as from tasks are generated from
                        // local files, and will not be completed repeatedly
                    }
                }

            } else if (taskTypeString.equals("E")) {
                String eventLineInput = taskLine.substring(secondDivisor + 1).trim();
                String taskCompletionStatus = taskLine.substring(firstDivisor + 2, secondDivisor).trim();
                Task newTask = tb.addEvent(eventLineInput.replace("| ", "/"));
                if (taskCompletionStatus.equals("1")) {
                    try {
                        newTask.markAsDone();
                    } catch (RepeatedCompletionException e) {
                        // This is left blank intentinally
                        // as from tasks are generated from
                        // local files, and will not be completed repeatedly
                    }
                }
            } else {
                throw new IOException("Wrong type from data loader");
            }
        }
    }
}
