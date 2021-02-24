package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.Toast;


import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;


public class SensorStatus extends Activity {

    Spinner floorSelect;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_status);

        floorSelect = (Spinner) findViewById(R.id.spinner);
        List<String> listOfFloor = new ArrayList<>();
        listOfFloor.add("Please select the floor");
        listOfFloor.add("1st Floor");
        listOfFloor.add("2nd Floor");
        listOfFloor.add("3rd Floor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item, listOfFloor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        floorSelect.setAdapter(adapter);
        floorSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String floorValue = parent.getItemAtPosition(position).toString();

                if(floorValue == "1st Floor") {
                    Intent intent = new Intent(SensorStatus.this, Floor1.class);
                    startActivity(intent);
                    Toast.makeText(SensorStatus.this, "selected " + floorValue , Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
