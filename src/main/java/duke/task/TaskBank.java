package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskBank {
    private Task[] tasks;
    private int taskIndex = 0;
    private static final int MAX_TASK = 100;
    private static final String filePath = "./data/duke.txt";

    {
        tasks = new Task[MAX_TASK];
    }

    public Task addTodo(String todoInput) {
        tasks[taskIndex++] = new ToDo(todoInput);
        return tasks[taskIndex - 1];
    }

    public Task addEvent(String todoInput) {
        tasks[taskIndex++] = new Event(todoInput);
        return tasks[taskIndex - 1];
    }

    public Task addDeadline(String todoInput) {
        tasks[taskIndex++] = new Deadline(todoInput);
        return tasks[taskIndex - 1];
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    public void printList() {
        int i;
        for (i = 0; i < taskIndex; i++) {
            System.out.printf("%d.%s%n", i + 1, tasks[i]);
        }
    }

    // search the Task in the array by index
    public Task searchTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex > this.taskIndex) {
            return null;
        }
        return tasks[taskIndex];
    }

    public void exportTasks() {

        StringBuffer rawData = new StringBuffer();
        for (int i = 0; i < this.taskIndex; i++) {
            rawData.append(tasks[i].getTaskType());
            rawData.append(" | ");
            rawData.append(tasks[i].getDone() ? "1" : "0");
            rawData.append(" | ");
            rawData.append(tasks[i].describe());
            rawData.append("\r\n");
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(rawData.toString());
            fw.close();
            System.out.println("saved");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTasks() {
        File f = new File(filePath);
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                loadTaskLine(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("duke.txt is not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    public void loadTaskLine(String taskLine) throws IOException {
        String taskTypeString = taskLine.substring(0, 1);
        int firstDivisor = taskLine.indexOf("|", 1);
        int secondDivisor = taskLine.indexOf("|", firstDivisor+1);
        int thirdDivisor = taskLine.indexOf("|", secondDivisor+1);
        if (thirdDivisor == -1) { // todo type
            addTodo(taskLine.substring(secondDivisor + 1).trim());
        } else {
            if (taskTypeString.equals("D")) {
                String deadLineInput = taskLine.substring(secondDivisor + 1).trim();
                String taskCompletionStatus = taskLine.substring(firstDivisor + 2, secondDivisor).trim();

                Task newTask = addDeadline(deadLineInput.replace("| ", "/"));
                if(taskCompletionStatus.equals("1")){
                    newTask.setDone();
                }

            } else if (taskTypeString.equals("E")) {
                String eventLineInput = taskLine.substring(secondDivisor + 1).trim();
                String taskCompletionStatus = taskLine.substring(firstDivisor + 2, thirdDivisor).trim();

                Task newTask = addEvent(eventLineInput.replace("| ", "/"));
                if(taskCompletionStatus.equals("1")){
                    newTask.setDone();
                }
            } else {
                throw new IOException("Wrong type from data loader");
            }
        }
    }

    public void clear(){
        tasks = new Task[MAX_TASK];
        exportTasks();
    }
}
