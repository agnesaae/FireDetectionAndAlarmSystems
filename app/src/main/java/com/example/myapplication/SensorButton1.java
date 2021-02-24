package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class SensorButton1 extends Activity {

    public DatabaseReference myRef; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase
    public DatabaseReference myRefS1; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase/child
    private TextView sensor1CO;
    private TextView sensor1LPG;
    private TextView sensor1SMOKE;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_data_sensor);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.3),(int)(height*.1));

        sensor1CO = (TextView) findViewById(R.id.sensor1CO);
        sensor1LPG = (TextView) findViewById(R.id.sensor1LPG);
        sensor1SMOKE = (TextView) findViewById(R.id.sensor1SMOKE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRefS1 = database.getReference().child("Sensor1"); //อ้างอิงไปยัง child ของ Sensor1
        myRefS1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String values1 = String.valueOf(map.get("CO")); //ลองเรียก CO ซึ่งเป็น child ของ Sensor1
                sensor1CO.setText(values1);
                String values2 = String.valueOf(map.get("LPG"));
                sensor1LPG.setText(values2);
                String values3 = String.valueOf(map.get("SMOKE"));
                sensor1SMOKE.setText(values3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
