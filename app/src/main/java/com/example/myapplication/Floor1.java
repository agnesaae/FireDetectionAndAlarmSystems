package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Floor1 extends Activity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floor_1st);

    }
    public void OnclickS1 (View view){
        Button btn_s1 = (Button)findViewById(R.id.sensorStatus);
        Intent intent = new Intent(Floor1.this,SensorButton1.class);
        startActivity(intent);
    }
    public void OnclickS2 (View view){
        Button btn_s2 = (Button)findViewById(R.id.sensorStatus2);
        Intent intent = new Intent(Floor1.this,SensorButton2.class);
        startActivity(intent);
    }
    public void OnclickS3 (View view){
        Button btn_s3 = (Button)findViewById(R.id.sensorStatus3);
        Intent intent = new Intent(Floor1.this,SensorButton3.class);
        startActivity(intent);
    }
}


