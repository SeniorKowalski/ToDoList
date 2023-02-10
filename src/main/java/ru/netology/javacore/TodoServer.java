package ru.netology.javacore;

import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private final int port;
    private final Todos todos;
    private final Logger logger = Logger.getInstance();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    String jsonRequest = in.readLine();
                    InitJson initJson = new GsonBuilder().create().fromJson(jsonRequest, InitJson.class);
                    operationToDo(initJson);
                    out.print(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public void operationToDo(InitJson jsonInit) {
        Task task = new Task(jsonInit.getTask());
        int minNumOfTasks = 0;
        switch (jsonInit.getType()) {
            case "ADD":
                if (todos.getTaskSet().size() < Todos.MAX_NUM_OF_TASKS) {
                    todos.addTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "REMOVE":
                if (todos.getTaskSet().size() > minNumOfTasks) {
                    todos.removeTask(task);
                    logger.log(jsonInit);
                }
                break;
            case "RESTORE":
                try {
                    InitJson lastJsonTask = logger.getLastLog();
                    Task lastTask = new Task(lastJsonTask.getTask());
                    if (lastJsonTask.getType().equals("ADD")) {
                        todos.getTaskSet().remove(lastTask);
                    } else {
                        todos.addTask(lastTask);
                    }
                    logger.logRemote();
                    break;
                } catch (Exception e) {
                    System.out.println("Nothing to restore");
                }
        }
    }
}