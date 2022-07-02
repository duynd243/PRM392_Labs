package com.prm392.todolistsqlite;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.prm392.todolistsqlite.adapter.TodoAdapter;
import com.prm392.todolistsqlite.database.Database;
import com.prm392.todolistsqlite.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database db;
    TodoAdapter todoAdapter;
    ListView todoListView;
    List<Todo> todoList;
    ImageView icAdd;

    private void loadTodos() {
        todoList.clear();
        Cursor cursor = db.getData("SELECT * FROM ToDoList ORDER BY todoId DESC");
        while (cursor.moveToNext()) {
            int todoId = cursor.getInt(0);
            String todoName = cursor.getString(1);
            todoList.add(new Todo(todoId, todoName));
        }
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        icAdd = findViewById(R.id.icAdd);
        todoList = new ArrayList<>();
        todoListView = findViewById(R.id.todoListView);
        todoAdapter = new TodoAdapter(this, R.layout.row_todo, todoList);
        todoListView.setAdapter(todoAdapter);
        db = new Database(this, "ToDoList.sqlite", null, 1);
        db.queryData("CREATE TABLE IF NOT EXISTS ToDoList(todoId INTEGER PRIMARY KEY AUTOINCREMENT, todoName NVARCHAR(200))");

        loadTodos();

        icAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Todo");
            EditText editText = new EditText(this);
            builder.setView(editText);
            builder.setPositiveButton("Add", (dialog, which) -> {
                String newTodoName = editText.getText().toString();
                if (newTodoName.trim().isEmpty()) {
                    Toast.makeText(this, "Todo name cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    db.queryData("INSERT INTO ToDoList VALUES(null, '" + newTodoName + "')");
                    loadTodos();
                }
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            builder.show();
        });
    }


    public void dialogRemoveTodo(Todo todo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Confirmation");
        builder.setMessage("Are you sure to remove " + todo.getTodoName() + "?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            db.queryData("DELETE FROM ToDoList WHERE todoId = " + todo.getTodoId());
            loadTodos();
            Toast.makeText(this, "Removed " + todo.getTodoName(), Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    public void dialogUpdateTodo(Todo todo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Todo");
        EditText editText = new EditText(this);
        editText.setText(todo.getTodoName());
        builder.setView(editText);
        builder.setPositiveButton("Update", (dialog, which) -> {
            String newTodoName = editText.getText().toString();
            if (newTodoName.trim().isEmpty()) {
                Toast.makeText(this, "Todo name cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                db.queryData("UPDATE ToDoList SET todoName = '" + newTodoName + "' WHERE todoId = " + todo.getTodoId());
                Toast.makeText(this, "Updated " + todo.getTodoName() + " to " + newTodoName, Toast.LENGTH_SHORT).show();
                loadTodos();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


}