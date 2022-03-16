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
                8986, 0x1F603, 0x1F605, 0x1F60D, 0x1F60F,
                0x1F618, 0x1F621, 0x1F625, 0x1F628, 0x1F62D,
                0x1F637, 0x1F61D, 0x1F616, 0x1F609, 0x1F60B,
                0x1F635, 0x1F633, 0x1F624, 0x1F61C, 0x1F60A);
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
                            "Win! " ,Toast.LENGTH_SHORT).show();
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