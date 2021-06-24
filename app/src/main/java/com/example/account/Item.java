package com.example.account;

public class Item {
    private boolean IsIncome;// 是否为收入  1 为收入、 0 为 支出
    private String type; // 类型
    private long time;// 时间戳 使用工具类转换
    private String remark; // 说明
    private double price; // 金额

    private int ids;

    public Item(boolean isIncome, String type, long time, String remark, double price) {
        IsIncome = isIncome;
        this.type = type;
        this.time = time;
        this.remark = remark;
        this.price = price;
    }

    public Item() {

    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public boolean isIncome() {
        return IsIncome;
    }

    public void setIncome(boolean income) {
        IsIncome = income;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
