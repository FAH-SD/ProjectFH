package com.example.goldas.projectfh;

/**
 * Created by roy on 2015/8/16.
 */
public class ListViewItem {
    private String item, quantity, unit, limitdate;
    private int type;

    public ListViewItem(String item, String quantity, String unit, String limitdate, int type) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
        this.limitdate = limitdate;
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuan() {
        return quantity;
    }

    public void setQuan(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getLimitdate() {
        return limitdate;
    }

    public void setLimitdate(String limitdate) {
        this.limitdate = limitdate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}