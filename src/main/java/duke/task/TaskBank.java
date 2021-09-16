package duke.task;


import duke.exception.RepeatedCompletionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskBank {
    private ArrayList<Task> tasks;
    public static final String directoryPath = "./data/";
    public static final String filePath = "./data/duke.txt";

    {
        tasks = new ArrayList<>();
    }


    public Task addTodo(String todoInput) {
        Task newTask = new ToDo(todoInput);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String todoInput) {
        Task newTask = new Event(todoInput);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadline(String todoInput) {
        Task newTask = new Deadline(todoInput);
        tasks.add(newTask);
        return newTask;
    }

    public int getTaskSize() {
        return tasks.size();
    }

    public void printList() {
        int i = 0;
        for (Task task : tasks) {
            System.out.printf("%d.%s%n", i + 1, task);
            i++;
        }
    }

    // search the Task in the array by index
    public Task searchTask(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex > this.getTaskSize()) {
            throw new IndexOutOfBoundsException("Ouch! Index is out of range. Try again!\n");
        }
        return tasks.get(taskIndex);
    }

    public Task removeTask(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex > this.getTaskSize()) {
            throw new IndexOutOfBoundsException("Ouch! Index is out of range. Try again!\n");
        }
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return deletedTask;
    }

    public void exportTasks() {
        StringBuffer taskTextString = new StringBuffer();
        for (Task task : tasks) {
            taskTextString.append(task.getTaskType());
            taskTextString.append(" | ");
            taskTextString.append(task.getDone() ? "1" : "0");
            taskTextString.append(" | ");
            taskTextString.append(task.describe());
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
        }

    }

    public void loadTaskLine(String taskLine) throws IOException {
        String taskTypeString = taskLine.substring(0, 1);
        int firstDivisor = taskLine.indexOf("|", 1);
        int secondDivisor = taskLine.indexOf("|", firstDivisor + 1);
        int thirdDivisor = taskLine.indexOf("|", secondDivisor + 1);
        if (thirdDivisor == -1) { // todo type
            addTodo(taskLine.substring(secondDivisor + 1).trim());
        } else {
            if (taskTypeString.equals("D")) {
                String deadLineInput = taskLine.substring(secondDivisor + 1).trim();
                String taskCompletionStatus = taskLine.substring(firstDivisor + 2, secondDivisor).trim();
                Task newTask = addDeadline(deadLineInput.replace("| ", "/"));
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
                Task newTask = addEvent(eventLineInput.replace("| ", "/"));
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

    public void clear() {
        tasks = new ArrayList<>();
        exportTasks();
    }
}
