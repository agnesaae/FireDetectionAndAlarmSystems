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


public class SensorButton2 extends Activity {

    public DatabaseReference myRef; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase
    public DatabaseReference myRefS2; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase/child
    private TextView sensor2CO;
    private TextView sensor2LPG;
    private TextView sensor2SMOKE;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_data_sensor2);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.3),(int)(height*.1));

        sensor2CO = (TextView) findViewById(R.id.sensor2CO);
        sensor2LPG = (TextView) findViewById(R.id.sensor2LPG);
        sensor2SMOKE = (TextView) findViewById(R.id.sensor2SMOKE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRefS2 = database.getReference().child("Sensor2"); //อ้างอิงไปยัง child ของ Sensor2
        myRefS2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String values1 = String.valueOf(map.get("CO")); //ลองเรียก CO ซึ่งเป็น child ของ Sensor2
                sensor2CO.setText(values1);
                String values2 = String.valueOf(map.get("LPG"));
                sensor2LPG.setText(values2);
                String values3 = String.valueOf(map.get("SMOKE"));
                sensor2SMOKE.setText(values3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
