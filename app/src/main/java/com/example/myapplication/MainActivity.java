package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference myRef;
    private TextView mFirebaseTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseTextview = (TextView) findViewById(R.id.firebaseTextview);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map)dataSnapshot.getValue();
                String value = String.valueOf(map.get("Data"));
                if(value == "Detected"){
                    addNotification();
                }
                mFirebaseTextview.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addNotification(){
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle("Fire Alarm Detected")
                .setContentText("Check for your own safety");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }

    public void OnclickLive (View view){
        Button btn_live = (Button)findViewById(R.id.liveStream);
        Intent intent = new Intent(MainActivity.this,LiveStreaming.class);
        startActivity(intent);

    }

    public void OnclickEmer (View view){
        Button btn_emr = (Button)findViewById(R.id.emergencyCall);
        Intent intent = new Intent(MainActivity.this,EmergencyCall.class);
        startActivity(intent);addNotification();
    }

    public void OnclickInfo (View view){
        Button btn_inf = (Button)findViewById(R.id.inforMaker);
        Intent intent = new Intent(MainActivity.this,InforMaker.class);
        startActivity(intent);


    }
    public void OnclickSensorStatus (View view){
        Button btn_sss = (Button)findViewById(R.id.sensorStatus);
        Intent intent = new Intent(MainActivity.this,SensorStatus.class);
        startActivity(intent);
    }


}
