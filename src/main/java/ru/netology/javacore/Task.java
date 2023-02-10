package ru.netology.javacore;

public class Task implements Comparable<Task> {

    private final String task;

    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return task;
    }

    @Override
    public int compareTo(Task o) {
        return task.compareTo(o.getTask());
    }
}