package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class EmergencyCall extends Activity {

    public DatabaseReference myRefNos; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase
    public DatabaseReference myRefNo; //ตัวแปรอ้างอิงตัวแหน่งที่ชี้ใน firebase/child
    private static int REQUEST_CALL = 1;
    //private String contact[] = {"1234","5678","5465","5654"};
    String[] contact = new String [] {"1234","5678","5465","5654"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_call);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRefNos = database.getReference();
        myRefNo = database.getReference().child("Numbers"); //อ้างอิงไปยัง child ของ Numbers
        myRefNo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();
                String values1 = String.valueOf(map.get("No1"));
                contact[0] = values1;
                String values2 = String.valueOf(map.get("No2"));
                contact[1] = values2;
                String values3 = String.valueOf(map.get("No3"));
                contact[2] = values3;
                String values4 = String.valueOf(map.get("No4"));
                contact[3] = values4;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button btn_cops = (Button) findViewById(R.id.contact1cops);
        btn_cops.setText("Police");
        Button btn_firefighter = (Button) findViewById(R.id.contact2Firefighter);
        btn_firefighter.setText("Fire Station");
        Button btn_ambulance = (Button) findViewById(R.id.contact3ambulance);
        btn_ambulance.setText("Ambulance");
        Button btn_addnew = (Button) findViewById(R.id.addcontact);
        btn_addnew.setText("Add New Contact");

        btn_cops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(contact[0]);
            }
        });
        btn_firefighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(contact[1]);
            }
        });
        btn_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(contact[2]);
            }
        });
        /*btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
    private void makePhoneCall(String contact){
        if(ContextCompat.checkSelfPermission(EmergencyCall.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EmergencyCall.this,new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall(contact[1]);
            }
        }
    }
}
