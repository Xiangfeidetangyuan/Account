package com.example.account.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.account.R;
import com.example.account.activity.addActivity;
import com.example.account.adapter.ListAdapter;
import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.entity.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class showFragment extends Fragment {


    ListView listView;
    List<Item> mList;
    Button cb;
    TextView tv,tv_output;
    ImageButton l,r;
    ItemDataBaseHelper idbh;
    int yearin,monthin,dayin;
    String note;
    private FloatingActionButton btn_add;

    public showFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView=getActivity().findViewById(R.id.listviewforaccount);
        cb=getActivity().findViewById(R.id.choosebt);
        l=getActivity().findViewById(R.id.lfbt);
        r=getActivity().findViewById(R.id.rgbt);
        tv=getActivity().findViewById(R.id.tv_note);
        btn_add = getActivity().findViewById(R.id.btn_add);

        tv_output =getActivity().findViewById(R.id.tv_output);
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
                tv=getActivity().findViewById(R.id.tv_output);
                tv.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(getContext());
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(getContext(),mList));
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
                tv=getActivity().findViewById(R.id.tv_output);
                tv.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(getContext());
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(getContext(),mList));
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
        idbh=new ItemDataBaseHelper(getContext());
        mList=idbh.queryItemByDay(mYear,mMonth+1,mDay);
        listView.setAdapter(new ListAdapter(getContext(),mList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                note = mList.get(position).getRemark();
                Log.d("TAG","note:"+note);
                tipDialog(note);

            }
        });

                btn_add = getActivity().findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent();
                intent.setClass(getContext(), addActivity.class);
                startActivity(intent);
            }
        });
    }
    private void showtime() {
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                yearin=i;
                monthin=i1+1;
                dayin=i2;
                dayin=dayin+0;
                monthin=monthin+0;
                yearin=yearin+0;
                tv_output.setText(yearin+"年"+monthin+"月"+dayin+"日");
                idbh=new ItemDataBaseHelper(getContext());
                mList=idbh.queryItemByDay(yearin,monthin,dayin);
                listView.setAdapter(new ListAdapter(getContext(),mList));
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
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
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

}