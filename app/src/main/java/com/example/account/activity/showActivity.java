package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.account.ListAdapter;
import com.example.account.R;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.entity.Item;
import com.example.account.util.DateUtil;

import java.util.Calendar;
import java.util.List;

public class showActivity extends AppCompatActivity {
    ListView listView;
    List<Item> mList;
    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button bt;
    ItemDataBaseHelper idbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView=findViewById(R.id.listviewforaccount);
        ed1=findViewById(R.id.yearinput);
        ed2=findViewById(R.id.monthinput);
        ed3=findViewById(R.id.dayinput);
        bt=findViewById(R.id.checkbt);

        //初始化
        ed1.setText(DateUtil.getCurrentYear()+"");
        ed2.setText(DateUtil.getCurrentMonth()+"");
        ed3.setText(DateUtil.getCurrentDay()+"");

        //实例化
        idbh = new ItemDataBaseHelper(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year=Integer.parseInt(ed1.getText().toString());
                int month=Integer.parseInt(ed2.getText().toString());
                int day=Integer.parseInt(ed3.getText().toString());
                mList=idbh.queryItemByDay(year,month,day);
                listView.setAdapter(new ListAdapter(showActivity.this,mList));
            }
        });
    }
    //重写回退方法
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(showActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
