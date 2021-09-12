package duke.task;

public class TaskBank {
    private Task[] tasks;
    private int taskIndex = 0;
    private static final int MAX_TASK = 100;

    {
        tasks = new Task[MAX_TASK];
    }

    public void addTask(String taskInput) {
        tasks[taskIndex++] = new Task(taskInput);
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
}
