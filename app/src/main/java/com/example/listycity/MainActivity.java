package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText cityEditText;
    Button addButton;
    Button deleteButton;

    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        cityEditText = findViewById(R.id.editText_name);

        addButton = findViewById(R.id.button_add);

        deleteButton = findViewById(R.id.button_clear);

        String[] cities = {"Edmonton", "Vancouver", "Toronto", "Hamilton", "Denver", "Los Angeles"};

        dataList = new ArrayList<>();

        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);

        cityList.setAdapter(cityAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = cityEditText.getText().toString();

                if (!newCity.isEmpty()) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                    cityEditText.setText("");
                }
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; 
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (selectedPosition != -1 && selectedPosition < dataList.size()) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();

                    selectedPosition = -1;
                }
            }
        });
    }
}
