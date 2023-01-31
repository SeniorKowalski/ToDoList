package ru.netology.javacore;

import java.util.TreeSet;

public class Todos {
    Task task;
    Logger logger = Logger.getInstance();
    TreeSet<Task> taskSet = new TreeSet<>();

    public void operationToDo(InitJson jsonInit) {
        task = new Task(jsonInit.getTask());
        switch (jsonInit.getType()) {
            case "ADD":
                if (taskSet.size() < 7) {
                    this.addTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "REMOVE":
                if (taskSet.size() > 0) {
                    this.removeTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "REMOTE":
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
                    System.out.println("Nothing to remote");
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
        Iterable<String> iterator = taskSet.stream().map(Object::toString)::iterator;
        return this.taskSet.isEmpty() ? "You have no tasks." : (String.join(" ", iterator));
    }
}