package ru.netology.javacore;

public class Main {

    public static void main(String[] args){
        Todos todos = new Todos();
        TodoServer server = new TodoServer(ServerConfig.PORT, todos);
        server.start();
    }
}