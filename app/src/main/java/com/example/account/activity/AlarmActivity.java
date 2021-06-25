package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.account.R;

public class AlarmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setIcon(R.drawable.tixing);
        alert.setTitle("闹钟：");
        alert.setMessage("今天您还有记账");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent  = new Intent();
                intent.setClass(AlarmActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alert.show();
    }
}