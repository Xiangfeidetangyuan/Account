package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.account.adapter.ListAdapter;
import com.example.account.R;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.entity.Item;

import java.util.Calendar;
import java.util.List;

public class showActivity extends AppCompatActivity {
    ListView listView;
    List<Item> mList;
    Button cb;
    TextView tv,tv_output;
    ImageButton l,r;
    ItemDataBaseHelper idbh;
    int yearin,monthin,dayin;
    String note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView=findViewById(R.id.listviewforaccount);
        cb=findViewById(R.id.choosebt);
        l=findViewById(R.id.lfbt);
        r=findViewById(R.id.rgbt);
        tv=findViewById(R.id.tv_note);

        tv_output =findViewById(R.id.tv_output);
        initTime();
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtime();


            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayin=dayin-1;
                tv=findViewById(R.id.tv_output);
                tv.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(showActivity.this);
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(showActivity.this,mList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        note = mList.get(position).getRemark();
                        Log.d("TAG","note:"+note);
                        tipDialog(note);
                    }
                });
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayin=dayin+1;
                tv=findViewById(R.id.tv_output);
                tv.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(showActivity.this);
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(showActivity.this,mList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        note = mList.get(position).getRemark();
                        Log.d("TAG","note:"+note);
                        tipDialog(note);

                    }
                });
            }
        });
    }
    private void initTime(){
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日

        tv_output.setText(mYear+"年"+(mMonth+1)+"月"+mDay+"日");

        yearin = mYear;
        monthin = mMonth+1;
        dayin =mDay;
        idbh=new ItemDataBaseHelper(showActivity.this);
        mList=idbh.queryItemByDay(mYear,mMonth+1,mDay);
        listView.setAdapter(new ListAdapter(showActivity.this,mList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                note = mList.get(position).getRemark();
                Log.d("TAG","note:"+note);
                tipDialog(note);

            }
        });
    }
    private void showtime() {
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                yearin=i;
                monthin=i1+1;
                dayin=i2;
                dayin=dayin+0;
                monthin=monthin+0;
                yearin=yearin+0;
                tv_output.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(showActivity.this);
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(showActivity.this,mList));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        note = mList.get(position).getRemark();
                        Log.d("TAG","note:"+note);
                        tipDialog(note);

                    }
                });

            }
        }, mYear,mMonth, mDay);//将年月日放入DatePickerDialog中，并将值传给参数

        datePickerDialog.show();//显示dialog

    }
    public void tipDialog(String s) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(showActivity.this);
        builder.setTitle("账单详情：");
        builder.setMessage(s);
        builder.setIcon(R.drawable.advise);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();      //创建AlertDialog对象
        dialog.show();                              //显示对话框
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent();
        intent.setClass(showActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}