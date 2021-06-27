package com.example.account.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.account.R;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.entity.Item;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private double ERROR = 0.001;

    private EditText eTType;
    private EditText eTPrice;
    private EditText eTRemark;
    private RadioButton rBIncom;
    private RadioButton rBExpenditure;
    private Button btn_remake;
    private ItemDataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        eTType = (EditText)findViewById(R.id.eTType);
        eTPrice = (EditText)findViewById(R.id.eTPrice);
        eTRemark = (EditText)findViewById(R.id.eTRemark);
        rBIncom = (RadioButton)findViewById(R.id.rBIncome);
        rBExpenditure = (RadioButton)findViewById(R.id.rBExpenditure);
        btn_remake = findViewById(R.id.button2);

        helper = new ItemDataBaseHelper(this);
        //监听器，限制输入最多两位小数**************************
        eTPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果第一个数字为0，第二个不为点，就不允许输入**************************
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        eTPrice.setText(s.subSequence(0, 1));
                        eTPrice.setSelection(1);
                        return;
                    }
                }
                //如果第一为点，直接显示0.**************************
                if (s.toString().startsWith(".")) {
                    eTPrice.setText("0.");
                    eTPrice.setSelection(2);
                    return;
                }
                //限制输入小数位数(2位)**************************
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 2 + 1);
                        eTPrice.setText(s);
                        eTPrice.setSelection(s.length());
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_remake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remake();
            }
        });
    }

    //重置**************************
    public void remake(){
        eTPrice.setText("");
        eTRemark.setText("");
        eTType.setText("");
        rBIncom.setChecked(true);
    }

    //记录按钮**************************
    public void record(View view){
        //获取信息**************************
        long time = System.currentTimeMillis();
        int isIncome = rBIncom.isChecked()?1:0;
        String type = eTType.getText().toString();
        String strprice = eTPrice.getText().toString();
        String remark = eTRemark.getText().toString();

        //判断输入是否完整合法**************************
        if(type.equals("")||strprice.equals("")||remark.equals("")){
            Toast.makeText(getApplicationContext(),"输入信息不完整", Toast.LENGTH_SHORT).show();
            return;
        }
        //price强制保留两位小数**************************
        DecimalFormat df = new DecimalFormat("#.00");
        Double price = Double.valueOf(strprice);
        strprice = df.format(price);
        price = Double.parseDouble(strprice);
        //判断price不为0**************************
        if(Math.abs(price)<ERROR){
            Toast.makeText(getApplicationContext(),"金额不能为0", Toast.LENGTH_SHORT).show();
        }
        //信息合法，写入数据库***************************
        else{

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String strtime = formatter.format(date);

            String txt = "";
            txt = txt + ((isIncome==1)?"收入":"支出");
            txt += "\n类型：" + type;
            txt += "\n金额：" + strprice;
            txt += "\n说明：" + remark;
            //转换后的时间，用于显示**********************************
            txt += "\n时间：" + strtime;
            //实例化item对象，已参照item类****************************
            Item item = new Item(rBIncom.isChecked(), type, time, remark, price);
            //确认框****************************************************************
            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
            builder.setTitle("记账确认");
            builder.setMessage(txt);

            builder.setPositiveButton("确认记录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    helper.insert(item);
                    remake();

                    /*******************************************/
                    /*****************写入数据库函数*************/
                    /*******************************************/
                }
            });

            builder.setNegativeButton("继续修改", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    return;
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(AddActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}