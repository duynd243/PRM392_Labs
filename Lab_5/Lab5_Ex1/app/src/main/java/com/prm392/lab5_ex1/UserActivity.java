package com.prm392.lab5_ex1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.prm392.lab5_ex1.adapter.UserAdapter;
import com.prm392.lab5_ex1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView rvUserList = findViewById(R.id.rvUserList);

        users = new ArrayList<>();
        users.add(new User("duynd", "Nguyen Dinh Duy", "duyndse@gmail.com"));
        users.add(new User("nghiatt", "Ton Trong Nghia", "nghiatt@gmail.com"));
        users.add(new User("nguyenndk", "Ngo Dinh Khoi Nguyen", "nguyenndk@gmail.com"));
        users.add(new User("trungnt", "Nguyen Thanh Trung", "trungnt@gmail.com"));
        users.add(new User("hungtm", "Tran Manh Hung", "hungtm@gmail.com"));

        UserAdapter userAdapter = new UserAdapter(users);
        rvUserList.setAdapter(userAdapter);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }
}