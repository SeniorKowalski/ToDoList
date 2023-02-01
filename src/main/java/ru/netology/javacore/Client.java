package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static ru.netology.javacore.ServerConfig.HOST;

public class Client {
    public static void main(String... args) {
        try (Socket socket = new Socket(HOST, ServerConfig.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//            writer.println("{'type': 'ADD', 'task': 'Первая'}");
//            writer.println("{'type': 'REMOVE', 'task': 'Первая'}");
//            writer.println("{'type': 'RESTORE'}");
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
