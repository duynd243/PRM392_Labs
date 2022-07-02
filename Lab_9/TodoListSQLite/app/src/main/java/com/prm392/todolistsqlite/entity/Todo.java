package com.prm392.todolistsqlite.entity;

public class Todo {
    private int todoId;
    private String todoName;

    public Todo(int todoId, String todoName) {
        this.todoId = todoId;
        this.todoName = todoName;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }
}
