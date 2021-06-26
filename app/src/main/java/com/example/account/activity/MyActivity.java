package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.account.R;

import java.util.Calendar;

public class MyActivity extends AppCompatActivity {

    TimePicker timepicker;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        timepicker = findViewById(R.id.timePicker1);
        timepicker.setIs24HourView(true);
        timepicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timepicker.setCurrentMinute(c.get(Calendar.MINUTE));

        LinearLayout tixing = findViewById(R.id.tixing);
        tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyActivity.this,AlarmActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MyActivity.this,0,intent,0);
                AlarmManager alarm = (AlarmManager)   getSystemService(Context.ALARM_SERVICE);
                c.set(Calendar.HOUR_OF_DAY,timepicker.getCurrentHour());
                c.set(Calendar.MINUTE,timepicker.getCurrentMinute());
                alarm.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                Toast.makeText(MyActivity.this,"提醒设置成功",Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MyActivity.this,ChangeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });








    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent();
        intent.setClass(MyActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}