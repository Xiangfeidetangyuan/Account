package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.account.R;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.util.DateUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
               // intent.setClass(MainActivity.this,add.class);
                intent.setClass(MainActivity.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ItemDataBaseHelper helper = new ItemDataBaseHelper(this);
        Log.d("TAG", DateUtil.getStartTimeStampByDay(2021,6,24)+":"+ DateUtil.getEndTimeStampByDay(2021,6,24));


    }
}