package com.example.account.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.account.R;
import com.example.account.adapter.MonthlyAccountAdapter;
import com.example.account.entity.MounthlyItem;
import com.example.account.util.DateUtil;


public class MontlyFragment extends Fragment {

    private Spinner spMouth;
    private TextView tvIncomingTotal;
    private TextView tvOutgoingTotal;
    private RecyclerView rvMounthItem;
    private MonthlyAccountAdapter adapter;
    private ArrayAdapter<String> spAdapter;
    private MounthlyItem itemsByMounth;
    private String[] mounths = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
    private int currentMounth;

    public MontlyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_montly, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        currentMounth= DateUtil.getCurrentMonth();
        itemsByMounth= new MounthlyItem(getContext());
        spAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_sp_mounth,R.id.tv_sp_mounth, mounths);
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
        adapter= new MonthlyAccountAdapter(R.layout.item_incoming_outgoing,itemsByMounth.getDataByMounth(currentMounth));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rvMounthItem.setLayoutManager(linearLayoutManager);
        rvMounthItem.setAdapter(adapter);
        spMouth.setSelection(currentMounth-1,true);
    }

    private void initView() {
        spMouth = getActivity().findViewById(R.id.sp_mounth);
        tvIncomingTotal = getActivity().findViewById(R.id.tv_incomings);
        tvOutgoingTotal = getActivity().findViewById(R.id.tv_outgoings);
        rvMounthItem = getActivity().findViewById(R.id.rv_mounthly);
    }

}