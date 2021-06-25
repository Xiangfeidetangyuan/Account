package com.example.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.account.entity.Item;
import com.example.account.util.DateUtil;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    List<Item> mList;

    public ListAdapter(List<Item>list)
    {
        mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=mLayoutInflater.inflate(R.layout.list_item,null);
        //取出数据赋值
       Item item=mList.get(position);
        TextView tv_title=view.findViewById(R.id.tv_title);
        TextView tv_date=view.findViewById(R.id.tv_date);
        TextView tv_money=view.findViewById(R.id.tv_money);
        //绑定
        tv_title.setText(mList.get(position).getType());

        //时间处理
        tv_date.setText(""+ DateUtil.timeStamp_Date(mList.get(position).getTime(),"MM-dd HH:mm"));
        tv_money.setText(""+mList.get(position).getPrice());
        return view;

    }

    private List<Item>getmList;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(Context context,List<Item>list)
    {
        mList=list;
        //通过外部传来的Context初始化LayoutInflater对象
        mLayoutInflater=LayoutInflater.from(context);
    }
}
