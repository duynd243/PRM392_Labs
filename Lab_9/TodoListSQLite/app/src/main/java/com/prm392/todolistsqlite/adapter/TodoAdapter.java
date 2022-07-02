package com.prm392.todolistsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.prm392.todolistsqlite.MainActivity;
import com.prm392.todolistsqlite.R;
import com.prm392.todolistsqlite.entity.Todo;

import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView todoName;
        ImageView icUpdate, icRemove;
    }

    private final MainActivity context;
    private final int rowTodoLayout;
    private final List<Todo> todoList;

    public TodoAdapter(MainActivity context, int rowTodoLayout, List<Todo> todoList) {
        this.context = context;
        this.rowTodoLayout = rowTodoLayout;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todoList.get(position).getTodoId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(rowTodoLayout, null);
            holder.todoName = view.findViewById(R.id.todoName);
            holder.icUpdate = view.findViewById(R.id.icUpdate);
            holder.icRemove = view.findViewById(R.id.icRemove);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Todo todo = todoList.get(position);
        holder.todoName.setText(todo.getTodoName());

        holder.icRemove.setOnClickListener(v -> context.dialogRemoveTodo(todo));

        holder.icUpdate.setOnClickListener(v -> context.dialogUpdateTodo(todo));

        return view;
    }
}

