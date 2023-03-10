package ru.netology.javacore;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TodosTests {

    private final Todos todos = new Todos();
    private final TodoServer todoServer = new TodoServer(ServerConfig.PORT,todos);
    private InitJson initJson = new InitJson();

    public List<String> param() {
        List<String> params = new ArrayList<>();
        params.add("{'type': 'ADD', 'task': 'Первая'}");
        params.add("{'type': 'ADD', 'task': 'Первая'}");
        params.add("{'type': 'ADD', 'task': 'Вторая'}");
        params.add("{'type': 'ADD', 'task': 'Третья'}");
        params.add("{'type': 'ADD', 'task': 'Четвёртая'}");
        params.add("{'type': 'ADD', 'task': 'Пятая'}");
        params.add("{'type': 'ADD', 'task': 'Шестая'}");
        params.add("{'type': 'ADD', 'task': 'Седьмая'}");
        params.add("{'type': 'ADD', 'task': 'Восьмая'}");
        params.add("{'type': 'REMOVE', 'task': 'Первая'}");
        params.add("{'type': 'REMOVE', 'task': 'Пятая'}");
        params.add("{'type': 'RESTORE'}");
        params.add("{'type': 'RESTORE'}");
        params.add("{'type': 'RESTORE'}");
        return params;
    }

    // Добавляем все задачи, должно добавиться только 7 (первая - седьмая).
    @Test
    public void addTodo() {
        for (int i = 0; i < 8; i++) {
            initJson = new GsonBuilder().create().fromJson(param().get(i), InitJson.class);
            todoServer.operationToDo(initJson);
        }
        Assertions.assertEquals(7, todos.getTaskSet().size());
    }

    // Добавляем все параметры и удаляем два. Должно остаться 5.
    @Test
    public void removeTodo() {
        for (int i = 0; i < param().size() - 3; i++) {
            initJson = new GsonBuilder().create().fromJson(param().get(i), InitJson.class);
            todoServer.operationToDo(initJson);
        }
        Assertions.assertEquals(5, todos.getTaskSet().size());
    }

    // Проверка команды RESTORE, должны остаться с первой по шестую позиции.
    @Test
    public void restore() {
        for (int i = 0; i < param().size(); i++) {
            initJson = new GsonBuilder().create().fromJson(param().get(i), InitJson.class);
            todoServer.operationToDo(initJson);
        }
        Assertions.assertEquals(6, todos.getTaskSet().size());
    }
}
