package com.example.account.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.account.R;
import com.example.account.activity.AlarmActivity;
import com.example.account.activity.ChangeActivity;

import java.util.Calendar;

public class MyFragment extends Fragment {
    TimePicker timepicker;
    Calendar c;


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        timepicker = getActivity().findViewById(R.id.timePicker1);
        timepicker.setIs24HourView(true);
        timepicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timepicker.setCurrentMinute(c.get(Calendar.MINUTE));

        LinearLayout tixing = getActivity().findViewById(R.id.tixing);
        tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlarmActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,0);
                AlarmManager alarm = (AlarmManager)  getActivity().getSystemService(Context.ALARM_SERVICE);
                c.set(Calendar.HOUR_OF_DAY,timepicker.getCurrentHour());
                c.set(Calendar.MINUTE,timepicker.getCurrentMinute());
                alarm.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                Toast.makeText(getContext(),"提醒设置成功",Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout change = getActivity().findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ChangeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });


    }
}