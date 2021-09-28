package duke.task;

import java.util.ArrayList;

public class TaskBank {
    private ArrayList<Task> tasks;

    public TaskBank() {
        tasks = new ArrayList<>();
    }

    public TaskBank(ArrayList<Task> givenTasks){
        tasks = givenTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
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

    public void clear() {
        tasks = new ArrayList<>();
    }


}
