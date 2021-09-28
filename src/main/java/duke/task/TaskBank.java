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


    /** Adds a todo to taskbank
     *
     * Creates a new todo task from the raw String input
     * @param todoInput
     * @return the newly created todo Task
     */
    public Task addTodo(String todoInput) {
        Task newTask = new ToDo(todoInput);
        tasks.add(newTask);
        return newTask;
    }

    /** Adds an event to taskbank
     *
     * Creates a new event task from the raw String input
     * @param eventInput
     * @return the newly created event Task
     */
    public Task addEvent(String eventInput) {
        Task newTask = new Event(eventInput);
        tasks.add(newTask);
        return newTask;
    }

    /** Adds a deadline to taskbank
     *
     * Creates a new event task from the raw String input
     * @param deadlilneInput
     * @return the newly created deadline Task
     */
    public Task addDeadline(String deadlilneInput) {
        Task newTask = new Deadline(deadlilneInput);
        tasks.add(newTask);
        return newTask;
    }

    public int getTaskSize() {
        return tasks.size();
    }

    /** Prints all tasks in TaskBank
     *
     */
    public void printList() {
        int i = 0;
        for (Task task : tasks) {
            System.out.printf("%d.%s%n", i + 1, task);
            i++;
        }
    }

    /** Returns  the target task from the TaskBank by specific index
     *
     * @param taskIndex the index of target task
     * @return the target index
     * @throws IndexOutOfBoundsException if index provided is out of bound
     */
    public Task searchTask(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex > this.getTaskSize()) {
            throw new IndexOutOfBoundsException("Ouch! Index is out of range. Try again!\n");
        }
        return tasks.get(taskIndex);
    }

    /** Removes the target task from the TaskBank
     *
     * @param taskIndex the index of target task
     * @return the removed Task
     * @throws IndexOutOfBoundsException if index provided is out of bound
     */
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

    /** Clears the content of the the Task Bank
     */
    public void clear() {
        tasks = new ArrayList<>();
    }


}
