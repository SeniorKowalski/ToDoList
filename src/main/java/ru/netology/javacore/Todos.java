package ru.netology.javacore;

import java.util.ArrayList;
import java.util.TreeSet;

public class Todos {
    Task task;

    TreeSet<Task> taskSet = new TreeSet<>();
    ArrayList<InitJson> logList = new ArrayList<>();

    public void operationToDo(InitJson jsonInit) {
        task = new Task(jsonInit.getTask());
        switch (jsonInit.getType()) {
            case "ADD":
                if (taskSet.size() < 7) {
                    this.addTask(task);
                    this.logList.add(jsonInit);
                }
                break;
            case "REMOVE":
                if (taskSet.size() > 0) {
                    this.removeTask(task);
                    this.logList.add(jsonInit);
                }
                break;
            case "REMOTE":
                try {
                    InitJson lastJsonTask = logList.get(logList.size() - 1);
                    Task lastTask = new Task(lastJsonTask.getTask());
                    if (lastJsonTask.getType().equals("ADD")) {
                        taskSet.remove(lastTask);
                    } else {
                        this.addTask(lastTask);
                    }
                    logList.remove(logList.size() - 1);
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