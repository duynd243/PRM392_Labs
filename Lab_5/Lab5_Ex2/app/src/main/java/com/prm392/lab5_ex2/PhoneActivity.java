package com.prm392.lab5_ex2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.prm392.lab5_ex2.adapter.PhoneAdapter;
import com.prm392.lab5_ex2.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends AppCompatActivity {

    List<Phone> phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        RecyclerView rvPhoneList = findViewById(R.id.rvPhoneList);

        phones = new ArrayList<>();
        phones.add(new Phone("iPhone SE", "Apple", 2020, 100, R.drawable.iphonese));
        phones.add(new Phone("iPhone 11", "Apple", 2020, 100, R.drawable.iphone11));
        phones.add(new Phone("iPhone 12", "Apple", 2020, 100, R.drawable.iphone12));
        phones.add(new Phone("iPhone 13", "Apple", 2020, 100, R.drawable.iphone13));
        phones.add(new Phone("iPhone 13 Pro", "Apple", 2020, 100, R.drawable.iphone13pro));

        rvPhoneList.setAdapter(new PhoneAdapter(phones));
        rvPhoneList.setLayoutManager(new LinearLayoutManager(this));

    }
}