package com.example.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.account.R;
import com.example.account.adapter.MonthlyAccountAdapter;
import com.example.account.entity.MounthlyItem;

public class MouthlyActivity extends AppCompatActivity {
    private Spinner spMouth;
    private TextView tvIncomingTotal;
    private TextView tvOutgoingTotal;
    private RecyclerView rvMounthItem;
    private MonthlyAccountAdapter adapter;
    private ArrayAdapter<String> spAdapter;
    private MounthlyItem itemsByMounth;
    private String[] mounths = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouthly);
        initView();
        itemsByMounth= new MounthlyItem(this);
        spAdapter = new ArrayAdapter<String>(this, R.layout.item_sp_mounth,R.id.tv_sp_mounth, mounths);
        spMouth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setNewInstance(itemsByMounth.getDataByMounth(i+1));
                tvIncomingTotal.setText(itemsByMounth.getIncomingByMounth(i+1)+"");
                tvOutgoingTotal.setText(itemsByMounth.getOutgoingByMounth(i+1)+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spMouth.setAdapter(spAdapter);
        adapter= new MonthlyAccountAdapter(R.layout.item_incoming_outgoing,itemsByMounth.getDataByMounth(1));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvMounthItem.setLayoutManager(linearLayoutManager);
        rvMounthItem.setAdapter(adapter);
        spMouth.setSelection(0,true);
    }

    private void initView() {
        spMouth = findViewById(R.id.sp_mounth);
        tvIncomingTotal = findViewById(R.id.tv_incomings);
        tvOutgoingTotal = findViewById(R.id.tv_outgoings);
        rvMounthItem = findViewById(R.id.rv_mounthly);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent();
        intent.setClass(MouthlyActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
