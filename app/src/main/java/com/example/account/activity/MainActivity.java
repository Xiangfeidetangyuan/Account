package com.example.account.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.account.R;
import com.example.account.adapter.MonthlyAccountAdapter;
import com.example.account.dao.ItemDataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private Button btn_query;
    private  Button btn_monthly;
    private Button   btn_my;
    private FloatingActionButton btn_add;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

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
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

        navigationView  = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Toast.makeText(MainActivity.this,"home",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_monthly:
                        break;
                    case R.id.navigation_person:
                        break;
                }
                return false;
            }
        });
    }
}