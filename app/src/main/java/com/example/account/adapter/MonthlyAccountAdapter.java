package com.example.account.adapter;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.account.R;
import com.example.account.entity.Item;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MonthlyAccountAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    private TextView tvTitle;
    private TextView tvInfo;
    private TextView tvNum;
    private LinearLayout llTitle;
    ArrayList<Item> items=new ArrayList<>();
    public MonthlyAccountAdapter(int layoutResId, @Nullable List<Item> data) {
        super(layoutResId, data);
        this.items= (ArrayList<Item>) data;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Item item) {
        tvTitle=baseViewHolder.getView(R.id.tv_title);
        tvInfo=baseViewHolder.getView(R.id.tv_info);
        tvNum=baseViewHolder.getView(R.id.tv_num);
        llTitle=baseViewHolder.getView(R.id.ll_title);
        tvTitle.setText(item.getType());
        if(item.getRemark()==null||item.getRemark().equals("")){
            tvInfo.setVisibility(View.GONE);
            llTitle.setGravity(Gravity.CENTER_VERTICAL);
        }
        else
            tvInfo.setText(item.getRemark());
        if(item.isIncome()){
            tvNum.setText("+"+item.getPrice());
        }
        else {
            tvNum.setText("-"+item.getPrice());
        }

    }
}
