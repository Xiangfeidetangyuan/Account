package com.example.account.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.account.entity.Item;
import com.example.account.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemDataBaseHelper {
    private static final String DATABASE_NAME = "account";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "accounts";
    private static final String ID = "_id";
    private static final String ISINCOME="IsIncome";
    private static final  String TYPE ="type";
    private static final String TIME = "time";
    private static final String PRICE = "price";
    private static final String REMARK = "remark";
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        private static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;
        private static final String CREATE_TABLE = "create table " +
                TABLE_NAME + "( " + ID + " integer primary key autoincrement, " +
                ISINCOME + " integer, " +  TYPE +" text, " + TIME + " long, " +
                PRICE + " double not null, " + REMARK + " text not null);";

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);


        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
        }

        public void reCreate(SQLiteDatabase db) {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_TABLE);
        }
    }

    public ItemDataBaseHelper(Context context) {
        helper = new DBOpenHelper(context);
        db = helper.getWritableDatabase();


        //init data
        initData();


    }

    private  void initData(){
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624522087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1621868461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624453087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624455461000,222.0,\"真开心\")");

        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624452087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1621868461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624523087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624455461000,222.0,\"真开心\")");

        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624362087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624278461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624363087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"红包\",1624275461000,222.0,\"真开心\")");

        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624122087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624128461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624003087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",16240065461000,222.0,\"真开心\")");

        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624102087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1624108461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1623903087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",16239065461000,222.0,\"真开心\")");



        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1619543087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1619864361000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1619524387589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (1,\"工资\",1619864361000,222.0,\"真开心\")");


        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (0,\"红包\",1621522087589,2.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (0,\"红包\",1623868461000,1.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (0,\"红包\",1621529087589,12.0,\"真开心\")");
        db.execSQL("INSERT INTO accounts (IsIncome,type,time,price,remark) VALUES (0,\"红包\",1623865461000,222.0,\"真开心\")");



    }

    //插入 条目到数据库
    public void insert(Item item) {
        ContentValues values = new ContentValues();
        values.put(ISINCOME,item.isIncome()?1:0);
        values.put(TYPE,item.getType());
        values.put(PRICE,item.getPrice());
        values.put(TIME,item.getTime());
        values.put(REMARK,item.getRemark());
        db.insert(TABLE_NAME, null, values);
        //db.close();
        Log.d("TAG", "DBHELPER:insert");

    }

    //获取所有 条目
    public List<Item> queryAll() {
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,ISINCOME,TYPE, PRICE,TIME,REMARK }, null, null, null, null, null);
        List<Item> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setIds(cursor.getInt(cursor.getColumnIndex(ID)));
            item.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
            item.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            item.setIncome(cursor.getInt(cursor.getColumnIndex(ISINCOME)) == 1);
            item.setTime(cursor.getLong(cursor.getColumnIndex(TIME)));
            items.add(item);
        }
        cursor.close();
        db.close();
        return items;
    }

    //查询一个月的账单记录
    public List<Item> queryItemByMonth(int year,int month) {
        long startTime = DateUtil.getStartTimeStampByMonth(year,month);
        long endTime = DateUtil.getEndTimeStampByMonth(year,month);
        Log.d("TAG",startTime+":"+endTime);
        List<Item> items = new ArrayList<>();
        String sql ="select * from "+TABLE_NAME+" where "+ TIME + " between " + startTime + " and " + endTime;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setIds(cursor.getInt(cursor.getColumnIndex(ID)));
            item.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
            item.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            item.setIncome(cursor.getInt(cursor.getColumnIndex(ISINCOME)) == 1);
            item.setTime(cursor.getLong(cursor.getColumnIndex(TIME)));
            items.add(item);
        }
        cursor.close();
        return  items;
    }

    //查询 一天的  记录
    public List<Item> queryItemByDay(int year,int month,int day) {
        long startTime = DateUtil.getStartTimeStampByDay(year,month,day);
        long endTime = DateUtil.getEndTimeStampByDay(year,month,day);
        Log.d("TAG",startTime+":"+endTime);
        List<Item> items = new ArrayList<>();
        String sql ="select * from "+TABLE_NAME+" where "+ TIME + " between " + startTime + " and " +  endTime;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setIds(cursor.getInt(cursor.getColumnIndex(ID)));
            item.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
            item.setType(cursor.getString(cursor.getColumnIndex(TYPE)));
            item.setIncome(cursor.getInt(cursor.getColumnIndex(ISINCOME)) == 1);
            item.setTime(cursor.getLong(cursor.getColumnIndex(TIME)));
            items.add(item);
        }
        cursor.close();
        return  items;
    }

}