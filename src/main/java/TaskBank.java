public class TaskBank {
    private Task[] tasks = new Task[100];
    private int taskIndex = 0;

    public void addTask(String taskInput) {
        tasks[taskIndex++] = new Task(taskInput);
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    public void printList() {
        int i;
        for (i = 0; i < taskIndex; i++) {
            System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }

    public Task searchTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > this.taskIndex) {
            return null;
        }
        return tasks[taskIndex];
    }
}
