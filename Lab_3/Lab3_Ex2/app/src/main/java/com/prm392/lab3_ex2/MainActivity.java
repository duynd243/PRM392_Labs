package com.prm392.lab3_ex2;

import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvLegends;
    List<Legend> legends;
    LegendAdapter legendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        legendAdapter = new LegendAdapter(this, R.layout.legend_listview, legends);
        lvLegends.setAdapter(legendAdapter);

    }

    private void mapping() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        lvLegends = findViewById(R.id.lvLegends);
        legends = new ArrayList<>();
        try {
            legends.add(new Legend("Pele", dateFormat.parse("23-10-1940"), R.drawable.ic_flag_brazil, R.drawable.pele));
            legends.add(new Legend("Lionel Messi", dateFormat.parse("24-06-1987"), R.drawable.ic_flag_argentina, R.drawable.messi));
            legends.add(new Legend("Ronaldo De Lima", dateFormat.parse("22-09-1976"), R.drawable.ic_flag_brazil, R.drawable.ronaldo));
            legends.add(new Legend("Diego Maradona", dateFormat.parse("30-10-1960"), R.drawable.ic_flag_argentina, R.drawable.maradona));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}