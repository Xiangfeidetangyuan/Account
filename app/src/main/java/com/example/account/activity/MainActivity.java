package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.account.R;
import com.example.account.adapter.MonthlyAccountAdapter;
import com.example.account.dao.ItemDataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Button btn_query;
    private  Button btn_monthly;
    private Button   btn_my;
    private FloatingActionButton btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(MainActivity.this,showActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ItemDataBaseHelper helper = new ItemDataBaseHelper(this);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(MainActivity.this, addActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_monthly = findViewById(R.id.btn_monthly);
        btn_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(MainActivity.this, MouthlyActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_my = findViewById(R.id.btn_my);
        btn_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(MainActivity.this,MyActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}