package com.example.account.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
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
    TextView tv;
    ImageButton l,r;
    ItemDataBaseHelper idbh;
    int yearin,monthin,dayin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView=findViewById(R.id.listviewforaccount);
        cb=findViewById(R.id.choosebt);
        l=findViewById(R.id.lfbt);
        r=findViewById(R.id.rgbt);
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
            }
        });
    }
    private void initTime(){
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日
        tv=findViewById(R.id.tv_output);
        tv.setText(mYear+"年"+(mMonth+1)+"月"+mDay+"日");

        yearin = mYear;
        monthin = mMonth+1;
        dayin =mDay;
        idbh=new ItemDataBaseHelper(showActivity.this);
        mList=idbh.queryItemByDay(mYear,mMonth+1,mDay);
        listView.setAdapter(new ListAdapter(showActivity.this,mList));
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
                tv=findViewById(R.id.tv_output);
                dayin=dayin+0;
                monthin=monthin+0;
                yearin=yearin+0;
                tv.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(showActivity.this);
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(showActivity.this,mList));
            }
        }, mYear,mMonth, mDay);//将年月日放入DatePickerDialog中，并将值传给参数

        datePickerDialog.show();//显示dialog

    }

}