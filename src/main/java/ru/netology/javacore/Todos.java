package ru.netology.javacore;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Todos {
    private final Logger logger = Logger.getInstance();
    private final TreeSet<Task> taskSet = new TreeSet<>();

    public void operationToDo(InitJson jsonInit) {
        Task task = new Task(jsonInit.getTask());
        int maxNumOfTasks = 7;
        int minNumOfTasks = 0;
        switch (jsonInit.getType()) {
            case "ADD":
                if (taskSet.size() < maxNumOfTasks) {
                    this.addTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "REMOVE":
                if (taskSet.size() > minNumOfTasks) {
                    this.removeTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "RESTORE":
                try {
                    InitJson lastJsonTask = logger.getLastLog();
                    Task lastTask = new Task(lastJsonTask.getTask());
                    if (lastJsonTask.getType().equals("ADD")) {
                        taskSet.remove(lastTask);
                    } else {
                        this.addTask(lastTask);
                    }
                    logger.logRemote();
                    break;
                } catch (Exception e) {
                    System.out.println("Nothing to restore");
                }
        }
    }

    public void addTask(Task task) {
        taskSet.add(task);
    }

    public void removeTask(Task task) {
        taskSet.remove(task);
    }

    public String getAllTasks() {
        return this.taskSet.isEmpty() ? "You have no tasks." : taskSet.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    public TreeSet<Task> getTaskSet() {
        return taskSet;
    }
}