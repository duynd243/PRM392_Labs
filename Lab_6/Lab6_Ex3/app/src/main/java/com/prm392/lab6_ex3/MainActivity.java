package com.prm392.lab6_ex3;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    Button btnChangeBackgroundColor;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.constraintLayoutMain);
        btnChangeBackgroundColor = findViewById(R.id.btnChangeBackgroundColor);
        registerForContextMenu(btnChangeBackgroundColor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        changeBackgroundColor(item.getItemId());
        return super.onContextItemSelected(item);
    }

    private void changeBackgroundColor(int menuItemId) {
        switch (menuItemId) {
            case R.id.menuItemRed:
                layout.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case R.id.menuItemGreen:
                layout.setBackgroundColor(getResources().getColor(R.color.green));
                break;

            case R.id.menuItemYellow:
                layout.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            default:
                break;
        }
    }
}