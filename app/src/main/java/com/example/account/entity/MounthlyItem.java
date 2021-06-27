package com.example.account.entity;

import android.content.Context;
import android.util.Log;

import com.example.account.dao.ItemDataBaseHelper;
import com.example.account.entity.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MounthlyItem {
    private int year;
    private boolean isUpdated=true;
    private ArrayList<Item> originData;
    private Map<Integer, List<Item>> itemByMounth;
    private Map<Integer,Integer> incomingTotals;
    private Map<Integer,Integer> outgoingTotals;
    private ItemDataBaseHelper itemDataBaseHelper;

    public MounthlyItem(Context context) {
        this.year=2021;
        this.originData = new ArrayList<>();
        this.itemByMounth = new HashMap<Integer, List<Item>>();
        this.incomingTotals = new HashMap<Integer,Integer>();
        this.outgoingTotals = new HashMap<Integer,Integer>();
        this.itemDataBaseHelper = new ItemDataBaseHelper(context);
    }

    public List<Item> getDataByMounth(Integer mounth){
        if(isUpdated)
        itemByMounth.put(mounth,itemDataBaseHelper.queryItemByMonth(year,mounth));
        return itemByMounth.get(mounth);
    }
    public double getIncomingByMounth(Integer mounth){
        int sum=0;
        for(Item item :  getDataByMounth(mounth)){
            if(item.isIncome())// 是否为收入  true为收入、 0 为 支出
            sum+=item.getPrice();
        }
        incomingTotals.put(mounth,sum);
        return sum;
    }
    public double getOutgoingByMounth(Integer mounth){
        int sum=0;
        for(Item item :  getDataByMounth(mounth)){
            if(!item.isIncome())// 是否为收入  true为收入、 0 为 支出
                sum+=item.getPrice();
        }
        outgoingTotals.put(mounth,sum);
        return sum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Item> getOriginData() {
        return originData;
    }

    public void setOriginData(ArrayList<Item> originData) {
        this.originData = originData;
    }

    public Map<Integer, List<Item>> getItemByMounth() {
        return itemByMounth;
    }

    public void setItemByMounth(Map<Integer, List<Item>> itemByMounth) {
        this.itemByMounth = itemByMounth;
    }

    public Map<Integer, Integer> getIncomingTotals() {
        return incomingTotals;
    }

    public void setIncomingTotals(Map<Integer, Integer> incomingTotals) {
        this.incomingTotals = incomingTotals;
    }

    public Map<Integer, Integer> getOutgoingTotals() {
        return outgoingTotals;
    }

    public void setOutgoingTotals(Map<Integer, Integer> outgoingTotals) {
        this.outgoingTotals = outgoingTotals;
    }

}
