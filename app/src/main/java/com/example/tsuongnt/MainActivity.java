package com.example.tsuongnt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView=findViewById(R.id.myGirdView);
        List<Integer> emoji = Arrays.asList(
                128011, 128007,	128008, 128001, 128012,
                128014, 128015, 128018, 128019, 128025,
                128022, 128028, 128030, 128035, 128039,
                128045, 128046, 128056, 128060, 128043);
        List data = new ArrayList();
        for (int i = 0; i < emoji.size(); i++) {
            data.add(new String(Character.toChars(emoji.get(i))));
        }
        IconService iconService = new IconService(getApplicationContext(), R.layout.icon_layout, data);
        gridView.setAdapter(iconService);

        List icons = new ArrayList(data);
        target = RandomIcon(icons);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v = (TextView) view;
                String selected = v.getText().toString();
                if(selected.equals(target)){
                    icons.remove(target);
                    v.setText("");
                }
                target = RandomIcon(icons);
                if(icons.size()==1)
                {
                    Toast.makeText(getApplicationContext(),
                            "Bạn thắng rồi" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String RandomIcon(List icons) {
        Random rand = new Random();
        String result = icons.get(rand.nextInt(icons.size())).toString();
        TextView target = findViewById(R.id.target);
        target.setText(result);
        return result;
    }
}