package ru.netology.javacore;

import java.util.TreeSet;
import java.util.stream.Collectors;

public class Todos {
    public static final int MAX_NUM_OF_TASKS = 7;
    private final TreeSet<Task> taskSet = new TreeSet<>();

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