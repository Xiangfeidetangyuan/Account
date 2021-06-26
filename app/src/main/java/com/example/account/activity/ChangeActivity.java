package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.account.R;
import com.example.account.dao.UserDataBaseOpenHelper;
import com.example.account.entity.User;

import java.util.ArrayList;

public class ChangeActivity extends AppCompatActivity {
    private EditText username;
    private EditText password0;
    private EditText password1;
    private EditText password2;
    private UserDataBaseOpenHelper mUserDataBaseOpenHelper;
    private Button change;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        password0 = findViewById(R.id.et_changeactivity_password0);
        password1 = findViewById(R.id.et_changeactivity_password1);
        password2 = findViewById(R.id.et_changeactivity_password2);
        change = findViewById(R.id.bt_changeactivity_change);

        mUserDataBaseOpenHelper = new UserDataBaseOpenHelper(this);

        Intent intent =getIntent();
        String username = intent.getStringExtra("username");

        back = findViewById(R.id.ib_changeactivity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password00 = password0.getText().toString().trim();
                String password11 = password1.getText().toString().trim();
                String password22 = password2.getText().toString().trim();

                ArrayList<User> data = mUserDataBaseOpenHelper.getAllData();
                boolean match = false;
                for (int i = 0; i < data.size(); i++) {
                    User user = data.get(i);
                    Log.d("ckfckf",username+"  "+password00);
                    Log.d("ckfckf1",user.getName()+","+user.getPassword());
                    if (username.equals(user.getName()) && password00.equals(user.getPassword())) {
                        match = true;
                        break;
                    }
                }
                if (match) {
                    if(!TextUtils.isEmpty(password11) && !TextUtils.isEmpty(password22) && password11.equals(password22))
                    {
                        mUserDataBaseOpenHelper.delete(username,password00);
                        mUserDataBaseOpenHelper.add(username, password11);
                        Toast.makeText(ChangeActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else
                        Toast.makeText(ChangeActivity.this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();

                    }
                else {
                        Toast.makeText(ChangeActivity.this, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }


        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(ChangeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}